@echo off
set JAR=pls.jar

set PORT=17001

set JVM_OPTS=-Xms512m -Xmx1024m

echo Starting %JAR% on port %PORT% (press Ctrl+C to stop)...
java %JVM_OPTS% -jar "%~dp0%JAR%" --server.port=%PORT%

echo.
echo Server stopped with exit code %ERRORLEVEL%.
pause