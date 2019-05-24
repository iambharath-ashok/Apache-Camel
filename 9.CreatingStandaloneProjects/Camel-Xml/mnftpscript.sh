#!/usr/bin/env bash

echo "Starting XCASH FTP Script: `date`"
echo "File to be FTP: $1"
crisFtpResults=`ftp -n hostname <<END_SCRIPT
                quote USER 123455
                quote PASS 123454
                site LRecl=350
                cd 'XCSH.#OR.TEST'
                put "$1"
                bye
                END_SCRIPT`
echo "Exit Status of FTP: $?"
echo "$crisFtpResults"
echo "End of Script"