#!/bin/usr/env bash

# uses the connect.c libary to tunnel through firewall
# https://bitbucket.org/gotoh/connect/wiki/Home
sftp -o "ProxyCommand ./lib/connect -5 -S proxy.internal.com:8000 sftp.remote.com 22" remoteUser@sftp.remote.com << END

get /remoteDir/file*.txt ./localDir
bye
END

numFiles=$(ls ./localDir/file*.txt 2> /dev/null | wc -l)

RET=1

if [ $numFiles -gt 0 ]
then
  echo "Success"
  RET=0
else
  echo "Failed"
fi

exit $RET
