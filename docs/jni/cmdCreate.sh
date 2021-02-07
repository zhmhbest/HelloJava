CLASS_NAME="HelloJNI"
if [ ! -f "$CLASS_NAME.h" ]; then
    javah -classpath ./src -jni -encoding "UTF-8" $CLASS_NAME
fi
if [ ! -f "$CLASS_NAME.c" ]; then
    echo "#include \"$CLASS_NAME.h\"">"$CLASS_NAME.c"
fi