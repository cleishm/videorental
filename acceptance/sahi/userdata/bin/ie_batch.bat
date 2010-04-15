SET SAHI_HOME=..\..
SET SAHI_USERDATA_DIR=..
%SAHI_HOME%\tools\toggle_IE_proxy.exe enable
java -cp %SAHI_HOME%\lib\ant-sahi.jar net.sf.sahi.test.TestRunner scripts/demo/demo.suite "C:\Program Files\Internet Explorer\IEXPLORE.EXE" http://sahi.co.in/demo/ default localhost 9999 3 iexplore.exe -nomerge
%SAHI_HOME%\tools\toggle_IE_proxy.exe disable
