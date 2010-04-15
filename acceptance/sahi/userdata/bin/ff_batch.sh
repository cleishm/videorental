#!/bin/bash
export SAHI_HOME=../..
export SAHI_USERDATA_DIR=$SAHI_HOME/userdata
java -cp $SAHI_HOME/lib/ant-sahi.jar net.sf.sahi.test.TestRunner scripts/demo/demo.suite "firefox" http://sahi.co.in/demo/ default localhost 9999 3 firefox "-profile $SAHI_USERDATA_DIR/browser/ff/profiles/sahi\$threadNo -no-remote"
