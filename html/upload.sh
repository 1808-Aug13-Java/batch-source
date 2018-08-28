#!/usr/bin/env bash

HOST='ftp.ruinjohnson.com'
USER='enebetqa'
PASSWD='Pirates23'
FILEtoPut='index.html'
DOMAIN='ruinjohnson.com'

ftp -i -n $HOST <<END_SCRIPT
quote USER $USER
quote PASS $PASSWD

cd $DOMAIN
put $FILEtoPut

quit
END_SCRIPT
exit 0
