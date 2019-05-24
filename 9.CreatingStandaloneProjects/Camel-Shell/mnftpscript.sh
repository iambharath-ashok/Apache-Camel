#!/usr/bin/env bash

echo "Starting XCASH FTP Script: `date`"
echo "File to be FTP: $1"
crisFtpResults=`ftp -n hostname <<END_SCRIPT
                quote USER 1234
                quote PASS K5PH3BX8
                site LRecl=350
                cd 'XCSH.#OR.TEST'
                put "$1"
                bye
                END_SCRIPT`
echo "\nExit Status of FTP: $?"
echo "$crisFtpResults"
echo "\nEnd of Script"