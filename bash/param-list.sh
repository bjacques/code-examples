#!/usr/bin/env bash

dir=$(cd `dirname $0` && pwd)
cd $dir

scriptName=`basename "$0"`

if [[ $# -lt 1 ]]
then
  echo "Invalid argument!"
  echo "Usage: ./${scriptName} <A|B|C>"
  exit 1
fi

abc=$1
if ! [[ "A|B|C" == *"$abc|"* ]]
then
  echo "Neither A or B or C was given"
  exit 1
fi

echo "You chose $abc"

exit $?
