MINGW_HOME='D:/ProgramFiles/Programming/mingw-w64/x86_64-8.1.0-posix-seh-rt_v6-rev0/mingw64'
gcc="$MINGW_HOME/bin/x86_64-w64-mingw32-gcc.exe"
gpp="$MINGW_HOME/bin/x86_64-w64-mingw32-g++.exe"

cmake \
    --no-warn-unused-cli \
    -DCMAKE_EXPORT_COMPILE_COMMANDS:BOOL=TRUE \
    -DCMAKE_BUILD_TYPE:STRING=Debug \
    -DCMAKE_C_COMPILER:FILEPATH=$gcc \
    -DCMAKE_CXX_COMPILER:FILEPATH=$gpp \
    -S./ \
    -B./build \
    -G "MinGW Makefiles"
