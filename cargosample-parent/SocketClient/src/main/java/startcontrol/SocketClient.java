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

	//���ڴ洢��ÿ��Case�ĳɹ�״̬ 1 ��ʾ�ɹ���0��ʾʧ��
	public static ConcurrentHashMap <String,String> statusMap = new ConcurrentHashMap<String,String>();
	
    //�ͻ��˿�ʼʱ��
	static long clientStartTime = System.currentTimeMillis();
	//�ܹ��Ѿ��������Ŀ
	static int tempNum = 0;
	//��Ҫ�����case����Ŀ
	static int totalNum  = 0;
	//���ڼ���������̵Ľ���
	public static void processMonitor(){
		++tempNum;
		float persent = ((float)tempNum/(float)totalNum)*100;
		System.out.println("������Ϊ��"+totalNum+"  �Ѿ��������� �� "+tempNum+" �ܽ���Ϊ��"+persent+"%"+"  ������ʱ��"+(System.currentTimeMillis() - clientStartTime));
	    if(tempNum!=0 &&totalNum==tempNum){
	    	
	    	DataBaseMethodHandler handler = new DataBaseMethodHandlerImpl();
	    	handler.initDBEnvironment();
	    	int id = handler.getMaxSSCdataID();
	    	String sql = "update tb_socketcommunicationconfirm set success_status='1' where id="+id;
	    	handler.updateSocketComConData(sql);
	    	handler.closeAll();
	    	System.out.println("����TestCase�ɹ�����!");
	    	
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
				
				//��ÿһDataObject���ݽ����߳�ȥ����ͬʱ���߳̽����̳߳�ȥ����
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
		//�����ͻ���
		Thread.sleep(2000);
		SocketClient client = new SocketClient();
		client.start();
		
	}
	


}
