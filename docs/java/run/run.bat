@ECHO OFF
SETLOCAL EnableDelayedExpansion
SET CLASSPATH=.
FOR /F "usebackq delims=" %%f IN (`DIR /B /A:-D /O:N "%CD%\lib"`) DO (
    SET CLASSPATH=!CLASSPATH!;lib\%%f
)
echo %CLASSPATH%
