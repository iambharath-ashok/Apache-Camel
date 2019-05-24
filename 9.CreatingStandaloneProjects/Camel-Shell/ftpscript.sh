echo "Guru Raghavendra Vaibhava"
crisFtpResults=`ftp -n hostname
		quote USER XCSHEPWF
		quote PASS K5PH3BX8
		bye`
echo "$crisFtpResults"