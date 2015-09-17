#!/bin/sh
totalNum=0

while true ;
do
   result1=`curl -w %{http_code}"\n" http://192.168.4.37:8080/web1/index.jsp|grep 200`
   
   if [ ! -z $result1 ] && [ $result1 -eq 200 ];
   then
        let totalNum=$totalNum+1
	break;
   fi
done

while true ;
do
   result2=`curl -w %{http_code}"\n" http://192.168.4.37:8080/web2/index.jsp|grep 200`
   if [ ! -z $result2 ] && [ $result2 -eq 200 ];
   then
        let totalNum=$totalNum+1
	break;
   fi
done

while true ;
do
   result3=`curl -w %{http_code}"\n" http://192.168.4.37:8080/web3/index.jsp|grep 200`
   if [ ! -z $result3 ] && [ $result3 -eq 200 ];
   then
        let totalNum=$totalNum+1
	 break;
   fi
done

while true ;
do
   result4=`curl -w %{http_code}"\n" http://192.168.4.37:18080/web1/index.jsp|grep 200`
   if [ ! -z $result4 ] && [ $result4 -eq 200 ];
   then
        let totalNum=$totalNum+1
	 break;
   fi
done

while true ;
do
   result5=`curl -w %{http_code}"\n" http://192.168.4.37:18080/web2/index.jsp|grep 200`
   if [ ! -z $result5 ] && [ $result5 -eq 200 ];
   then
        let totalNum=$totalNum+1
	 break;
   fi
done

while true ;
do
   result6=`curl -w %{http_code}"\n" http://192.168.4.37:18080/web4/index.jsp|grep 200`
   if [ ! -z $result6 ] && [ $result6 -eq 200 ];
   then
        let totalNum=$totalNum+1
	 break;
   fi
done

while true ;
do
   result7=`curl -w "\n"%{http_code}"\n" http://192.168.4.37:18080/SocketClient/index.jsp|grep 200`
   if [ ! -z $result7 ] && [ $result7 -eq 200 ];
   then
        let totalNum=$totalNum+1
	 break;
   fi
done

while true ;
do
   result8=`curl -w %{http_code}"\n" http://192.168.4.37:28080/web1/index.jsp|grep 200`
   if [ ! -z $result8 ] && [ $result8 -eq 200 ];
   then
        let totalNum=$totalNum+1
	 break;
   fi
done

while true ;
do
   result9=`curl -w %{http_code}"\n" http://192.168.4.37:28080/web5/index.jsp|grep 200`
   if [ ! -z $result9 ] && [ $result9 -eq 200 ];
   then
        let totalNum=$totalNum+1
	 break;
   fi
done
	
echo "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" $totalNum   
if [ $totalNum -eq 9 ];
then
   
	 cp -f /root/.jenkins/workspace/ffg_cargo_git/cargosample-parent/SocketServer/target/SocketServer-0.0.1-SNAPSHOT.jar /usr/java/jdk1.7.0_79/jre/lib/ext
	 java -Xms50m -Xmx250m startcontrol.SocketServer >> /usr/ffg_server/logout1.txt &
	 #STR=".:/usr/java/jdk1.7.0_79/lib:/usr/java/jdk1.7.0_79/jre/lib:/root/ffg_runhome/lib/httpasyncclient-cache-4.1.jar:/root/ffg_runhome/lib/jdom.jar:/root/ffg_runhome/lib/mysql-connector-java-5.1.7-bin.jar:/root/ffg_runhome/lib/httpclient-4.4.1.jar:/root/ffg_runhome/lib/jsch-0.1.53.jar:/root/ffg_runhome/lib/commons-codec-1.9.jar:/root/ffg_runhome/lib/httpclient-cache-4.4.1.jar:/root/ffg_runhome/lib/log4j-1.2.17.jar:/root/ffg_runhome/lib/commons-logging-1.2.jar:/root/ffg_runhome/lib/httpcore-4.4.1.jar:/root/ffg_runhome/lib/httpcore-nio-4.4.1.jar:/root/ffg_runhome/lib/httpasyncclient-4.1.jar:/root/ffg_runhome/lib/javacsv.jar:/root/ffg_runhome/lib/ProjectFile.jar"
 	 #export CLASSPATH=$STR
	 #java -Xms50m -Xmx250m startcontrol.SocketServer >> logout1.txt &
	 echo "all webapp is started"


fi
