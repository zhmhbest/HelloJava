# cmake版本要求
cmake_minimum_required(VERSION 3.10)

# 编译标准
set(CMAKE_C_STANDARD 11)

# 项目名称
project(jni)

# 头文件搜索路径
include_directories("$ENV{JAVA_HOME}/include" "$ENV{JAVA_HOME}/include/win32")

# 执行文件
aux_source_directory(. SRC_LIST)
add_library(jni SHARED ${SRC_LIST})

