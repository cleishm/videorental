@ECHO OFF
SET SAHI_HOME=..\..
SET SCRIPTS_PATH=scripts/%1
SET BROWSER=C:\Program Files\Mozilla Firefox\firefox.exe
SET BROWSER_PROCESS=firefox.exe
SET BROWSER_OPTION=-profile $userDir/browser/ff/profiles/sahi$threadNo -no-remote
SET START_URL=http://sahi.co.in/demo/
SET THREADS=3
SET LOG_DIR=default
java -cp %SAHI_HOME%\lib\ant-sahi.jar net.sf.sahi.test.TestRunner %SCRIPTS_PATH% "%BROWSER%" %START_URL% %LOG_DIR% localhost 9999 %THREADS% %BROWSER_PROCESS% "%BROWSER_OPTION%"