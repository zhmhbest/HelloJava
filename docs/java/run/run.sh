CLASSPATH=.
IFS=`echo -en "\n\b"`
for f in `ls ./lib`; do
    CLASSPATH=$CLASSPATH:"lib/$f"
done
echo $CLASSPATH