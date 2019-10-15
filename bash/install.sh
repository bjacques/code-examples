#!/usr/bin/env bash

set -e

dir=$(cd `dirname $0` && pwd)
cd ${dir}
scriptName=`basename "$0"`
usage="Usage: ./${scriptName} [install-directory]"
  
if [[ $# -lt 1 ]]
then
   echo "Missing argument!"
   echo -e ${usage}
   exit 1
fi

component=model-demo
version=`cat release.version`
componentDir=${1}
installDir=${componentDir}/releases/${component}/${version}
installLink=${componentDir}/${component}

echo "Installing to: ${installDir}"

mkdir -p ${installDir}

cp -r ./conf ${installDir}
cp -r ./lib ${installDir}
cp -r ./log ${installDir}
cp -r ./work ${installDir}
cp ./run.sh ${installDir}/

# record rollback version
currentVersion=`ls -l ${installLink} 2>/dev/null | grep ^1 | awk -F'/' '{print $NF}'`
if [ "${currentVersion}" != "${version}" ]
then
  echo "Capture rollback.version ${currentVersion}"
  echo ${currentVersion} > ./rollback.version
  cp rollback.version ${installDir}/
else
  echo "Installing the same version. no change to rollback.version"
fi

cp release.version ${installDir}/

# switch from current version to new version
if [ -L ${installLink} ]
then
  unlink ${installLink}
fi

ln -s ${installDir} ${installLink}
echo "Created soft link for ${installDir}"
echo "Current active version is ${installDir}"
echo "Install Completed"
