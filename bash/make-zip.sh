#!/usr/bin/env bash

set -e

dir=$(cd `dirname $0` && pwd)
cd ${dir}
scriptName=`basename "$0"`
usage="Usage: ./${scriptName} [build-version]"
  
# version is passed from build servers build.number
version=${1:-0}
component=model-demo
toZip=./target/${component}

rm -rf ${toZip}

mkdir -p ${toZip}/conf
cp ./src/main/script/*.properties ${toZip}/conf

mkdir -p ${toZip}/lib
cp ./target/*-jar-with-dependencies.jar ${toZip}/lib

mkdir -p ${toZip}/log
mkdir -p ${toZip}/work

cp ./install.sh ${toZip}
cp ./src/main/script/*.sh ${toZip}
chmod +x ${toZip}/*.sh

echo ${version} > ${toZip}/release.version
sed -i -e "s/app.version=.*$/app.version=${version}/" ${toZip}/conf/app.properties

cd ${toZip}
zipFile=${dir}/target/${component}-${version}.zip
zip -r ${zipFile} ./

echo -e "\nCreated ${zipFile}"
