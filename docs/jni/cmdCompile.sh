cmake --build ./build --config Debug --target jni -- -j 14
cp ./build/lib* ./out/production/jni
