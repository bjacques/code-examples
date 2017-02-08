#!/usr/bin/env bash
#
# Requires:
#	serializer-2.7.2.jar
#	xalan-2.7.2.jar
#
# Usage:
#	./xslt.sh input.xml input.xsl output.html

java -cp . \
	org.apache.xalan.xslt.Process \
	-IN $1 \
	-XSL $2 \
	-OUT $3


