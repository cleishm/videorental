@ECHO OFF
SET SAHI_HOME=..\..
SET SCRIPTS_PATH=scripts/%1
SET BROWSER=C:\Documents and Settings\%Username%\Local Settings\Application Data\Google\Chrome\Application\chrome.exe
SET BROWSER_PROCESS=chrome.exe
SET BROWSER_OPTION=--user-data-dir=$userDir\browser\chrome\profiles\sahi$threadNo
SET START_URL=http://sahi.co.in/demo/
SET THREADS=3
SET LOG_DIR=default
%SAHI_HOME%\tools\toggle_IE_proxy.exe enable
java -cp %SAHI_HOME%\lib\ant-sahi.jar net.sf.sahi.test.TestRunner %SCRIPTS_PATH% "%BROWSER%" %START_URL% %LOG_DIR% localhost 9999 %THREADS% %BROWSER_PROCESS% "%BROWSER_OPTION%"
