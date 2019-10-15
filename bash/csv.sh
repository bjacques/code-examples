#!/usr/bin/env bash

# select a subset of fields
cat input.csv | grep "|" | awk -f'|' '{OFS="|"; print $1 $3 $5}' > ouput.csv

