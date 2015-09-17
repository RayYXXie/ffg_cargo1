b=`ps -ef | grep tomcat | grep -v 'tomcat-jenkins' | grep -v 'grep'|wc -l`
if [ $b \> 0  ]
then
        kill -9 `ps -ef | grep tomcat | grep -v 'tomcat-jenkins' | grep -v 'grep'|awk '{print $2}'`
else
        echo "No tomcats need to be killed"
fi


b1=`ps -ef | grep 'startcontrol.SocketServer' | grep -v 'grep'|wc -l`
if [ $b1 \> 0  ]
then
        kill -9 `ps -ef | grep 'startcontrol.SocketServer' | grep -v 'grep'|awk '{print $2}'`
else
        echo "No SockectServers need to be killed"
fi
