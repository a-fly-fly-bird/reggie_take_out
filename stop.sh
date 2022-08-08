echo 停止原来运行中的工程
APP_NAME=helloworld

# shellcheck disable=SC2006
# shellcheck disable=SC2009
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`

echo "程序的tpid是：$tpid"

if [ "${tpid}" ]; then
    echo 'Stop Process...'
    kill -15 "$tpid"
fi
echo 停止成功