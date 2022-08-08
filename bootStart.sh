#!/bin/sh
echo =================================
echo  自动化部署脚本启动
echo =================================

echo 停止原来运行中的工程
APP_NAME=helloworld

# shellcheck disable=SC2006
# shellcheck disable=SC2009
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ "${tpid}" ]; then
    echo 'Stop Process...'
    kill -15 "$tpid"
fi
sleep 2
# shellcheck disable=SC2006
# shellcheck disable=SC2009
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ "${tpid}" ]; then
    echo 'Kill Process!'
    kill -9 "$tpid"
else
    echo 'Stop Success!'
fi

echo 准备从git仓库拉取最新代码
cd /Users/tanyong/Desktop/reggie_take_out

echo 开始从git仓库拉取最新代码
git pull
echo 代码拉取完成

echo 开始打包
# shellcheck disable=SC2006
output=`mvn clean package -Dmaven.test.skip=true`

cd target

echo 启动项目
# shellcheck disable=SC2039
nohup java -jar reggie_take_out-1.0-SNAPSHOT.jar &> helloworld.log &
echo 项目启动完成
