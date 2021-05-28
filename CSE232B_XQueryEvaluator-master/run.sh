#!/bin/sh

echo "Rebuilding XPath..."
javac *.java
echo "Done rebuilding XPath"
echo

# Start running queries
count=1
while IFS= read -r line
do
  firstchar=`echo $line | cut -c1-1`
  if [ "$firstchar" != "#" ]
  then
    echo "Test case $count: $line "
    tmpfile=$(mktemp /tmp/input.txt)
    echo $line > "$tmpfile"
    java XPathMain "$tmpfile" > q$count.out
    rm "$tmpfile"
    ((count++))
  fi
done < "$1"

echo
echo "Completed test cases"