echo "#Search and return lines that contain gold in file 'coins'"
awk '/gold/' coins.txt

echo "#Match on lines beginning with a specific word"
awk '/^mail:/' ldif.txt

echo "#Match on lines beginning with a specific word and containing a predefined string"
awk '/^mail:.*doe.*/' ldif.txt

echo "#Search columns and return line number and line match"
awk -F" " '{if($1=="ndc" && $2=="ndc") {print NR"\t"$0}}' columns.txt

echo "#Find a String under specific conditions"
awk '{if ($3 > 1985) print "Type: " $1 " Year: " $3}' coins.txt

echo "Match only a specific field"
awk '$4 ~ /USA/' coins.txt

echo "Count lines of lines matching specific text"
awk 'END {print NR, "coins"}' coins.txt

awk 'BEGIN { count=0;} $4 ~ /USA/ { count ++; } END {print "Number of coins from the USA =",count;}' coins.txt

echo "Print parts of line"
awk '/^dn:/ {print $0}; /^uid:/ {print $0}; /^mail:/ {print $0; print " "};' ldif.txt

