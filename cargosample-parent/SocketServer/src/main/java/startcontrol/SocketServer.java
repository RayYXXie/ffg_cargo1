package startcontrol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import dao.DataBaseMethodHandler;
import dao.DataBaseMethodHandlerImpl;
import pojo.SocketCommunicationConfirmData;
import util.ServerSocketHandlerThreadPool;

public class SocketServer {

	static int count = 0;
	public static int totalNum=0;
	static boolean FLAG = true;
    static int controlcount=0;
	
	public void start() throws Exception {
		
		ServerSocket ss = null;
		DataBaseMethodHandler dataHandler = new DataBaseMethodHandlerImpl();
		dataHandler.initDBEnvironment();
		totalNum = dataHandler.selectIniCount();
		try {
			ss = new ServerSocket(7777);
			System.out.println("start to accept...");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//初始socket通讯确认数据
		DataBaseMethodHandler handler = new DataBaseMethodHandlerImpl();
		handler.initDBEnvironment();
		handler.createSocketComConData();
		int id = handler.getMaxSSCdataID();
		SocketCommunicationConfirmData data = null;
		
		
		while (true) {
			try {
				FIRST:
				if(controlcount == 0){
					HttpClient httpclient = new DefaultHttpClient();
					String url = "http://localhost:18080/SocketClient/HandlerServlet?id="+id;
					HttpGet httpget = new HttpGet(url);
					try {
						httpclient.execute(httpget);
						System.out.println("Server  = "+System.currentTimeMillis());
						SECOND:
						while(true){
							Thread.sleep(1000);
							data = handler.querySCCdata(id);
							if("1".equals(data.getCommunication_status())){
								String updateSql="update tb_socketcommunicationconfirm set communication_status='2' where id="+id;
								handler.updateSocketComConData(updateSql);
								//成功之后将所有的数据库连接关掉
								handler.closeAll();
								++controlcount;
								break FIRST;
							}else{
								if(++controlcount<100){
									break SECOND;
								}else{
									//失败之后将所有的数据库连接关掉
									handler.closeAll();
									throw new Exception("fail to communicate with the socketclient !");
								}
								
							}
						}
						
					} catch (ClientProtocolException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				System.out.println(System.currentTimeMillis());
				System.out.println("totalNum :"+totalNum+"  count: "+ count);
				if(count==SocketServer.totalNum && count!=0){
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}else{
					//监听端口，并接受来访socket
					Socket socket = ss.accept();
					++count;
					System.out.println(count);
					ServerSocketHandlerThreadPool.addThreadToPoll(socket);	
				}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		shutdownPools();
		

	}
	
	/**
	 * 关闭线程池
	 */
	public static void shutdownPools(){
		ServerSocketHandlerThreadPool.pool.shutdown();
	}
	
	
	public static void main(String args[]) throws Exception {

		SocketServer server = new SocketServer();
		server.start();

	}

}
