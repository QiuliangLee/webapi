PROC_PID=`ps -ef|grep "webapi-1.0-SNAPSHOT.jar"|grep -v grep|awk '{print $2}'`

if [ -z "$PROC_PID" ]
then
    nohup java  -jar  -Dfile.encoding=utf-8 -Dlog.path=/bocpet/bocpet-backend/log ./webapi-1.0-SNAPSHOT.jar   >/dev/null 2>&1  &
else
 echo "bocpet-backend正在运行，启动失败!"
 exit
fi

#启动校验
echo "启动校验，10s后结束"
sleep 10

PROC_PID=`ps -ef|grep "webapi-1.0-SNAPSHOT.jar"|grep -v grep|awk '{print $2}'`

if [ -z "${PROC_PID}" ]
then
    echo "bocpet-backend启动失败!"
else
    echo "bocpet-backend启动成功!"
fi