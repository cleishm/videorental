@ECHO OFF
SET SAHI_HOME=..\..
SET SAHI_USERDATA_DIR=..
java -cp %SAHI_HOME%\lib\ant-sahi.jar net.sf.sahi.test.TestRunner scripts/demo/demo.suite "C:\Program Files\Mozilla Firefox\firefox.exe" http://sahi.co.in/demo/ default localhost 9999 3 firefox.exe "-profile %SAHI_USERDATA_DIR%/browser/ff/profiles/sahi$threadNo -no-remote"