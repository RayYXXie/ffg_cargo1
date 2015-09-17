package startcontrol;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import dao.DataBaseMethodHandler;
import dao.DataBaseMethodHandlerImpl;
import pojo.InitialDataObject;
import util.ClientSocketHandlerPool;

public class SocketClient{

	//用于存储调每条Case的成功状态 1 表示成功，0表示失败
	public static ConcurrentHashMap <String,String> statusMap = new ConcurrentHashMap<String,String>();
	
    //客户端开始时间
	static long clientStartTime = System.currentTimeMillis();
	//总共已经处理的数目
	static int tempNum = 0;
	//需要处理的case总数目
	static int totalNum  = 0;
	//用于监控整个流程的进度
	public static void processMonitor(){
		++tempNum;
		float persent = ((float)tempNum/(float)totalNum)*100;
		System.out.println("总条数为："+totalNum+"  已经处理条数 ： "+tempNum+" 总进度为："+persent+"%"+"  现已用时："+(System.currentTimeMillis() - clientStartTime));
	    if(tempNum!=0 &&totalNum==tempNum){
	    	
	    	DataBaseMethodHandler handler = new DataBaseMethodHandlerImpl();
	    	handler.initDBEnvironment();
	    	int id = handler.getMaxSSCdataID();
	    	String sql = "update tb_socketcommunicationconfirm set success_status='1' where id="+id;
	    	handler.updateSocketComConData(sql);
	    	handler.closeAll();
	    	System.out.println("本次TestCase成功运行!");
	    	
	    }
	    
	}
	
	
	
	public void start() {
		DataBaseMethodHandler dataHandler = new DataBaseMethodHandlerImpl();
		dataHandler.initDBEnvironment();
		List<InitialDataObject> list  = dataHandler.fetchAll();
		totalNum =list.size();
		System.out.println("SocketServer.totalNum =" +totalNum);
		System.out.println("totalNum =" +totalNum);
		try {
			for (int i = 0; i < list.size(); i++) {
				
				//將每一DataObject数据交给线程去处理，同时将线程交给线程池去管理
				Socket socket = new Socket("127.0.0.1", 7777);
				socket.setSoTimeout(180000);
				InitialDataObject object = (InitialDataObject) list.get(i);
				ClientSocketHandlerPool.addSocketToThreadPool(socket, object);
				
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			ClientSocketHandlerPool.shutDownThreadPool();
		}
	}

	public static void main(String args[]) throws ClassNotFoundException, InterruptedException {
		//启动客户端
		Thread.sleep(2000);
		SocketClient client = new SocketClient();
		client.start();
		
	}
	


}
