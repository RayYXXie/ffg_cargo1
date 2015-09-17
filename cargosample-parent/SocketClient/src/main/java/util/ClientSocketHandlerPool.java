package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import dao.DataBaseMethodHandler;
import dao.DataBaseMethodHandlerImpl;
import pojo.CXFreturnResult;
import pojo.ExpectedDataObject;
import pojo.InitialDataObject;
import pojo.ResultDataObject;
import startcontrol.SocketClient;

public class ClientSocketHandlerPool {
	
	//定义一个核心线程数为30的静态的线程池
	public static final ExecutorService threadPool = new ThreadPoolExecutor(8, 4000, 120, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>() );

	/**
	 * 将每一条数据交给一个线程，同时将线程交给线程池管理
	 * @param socket 传进来的通讯socket
	 * @param object 传进来的case的一条数据
	 */
	public static void addSocketToThreadPool(final Socket socket, final InitialDataObject object) {

		Runnable run = new Runnable() {

			public void run() {
				
				ObjectInputStream ois = null;
				ObjectOutputStream oos = null;
				Object obj = null;
				CXFreturnResult result = null;
				try {
					
					// 输入对象 flush（）
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(object);
					oos.flush();
					// 建立输入流
					ois = new ObjectInputStream(socket.getInputStream());
					obj = ois.readObject();
					result = (CXFreturnResult) obj;
					String dataId = result.getDataId();
					
					DataBaseMethodHandler dataHandler = new DataBaseMethodHandlerImpl();
					dataHandler.initDBEnvironment();
					ExpectedDataObject expectData = dataHandler.getDataObjectById(dataId);
					
					
					//Assert.assertEquals(result.getExpectedValue(),expectData.getExpectedValue());
					
					String passResult = "2";
					if(Float.parseFloat(result.getExpectedValue())== expectData.getExpectedValue()){
						passResult = "1";
					}
					
					ResultDataObject insertObj = new ResultDataObject();
					insertObj.setDataId(result.getDataId());
					insertObj.setAccount(result.getAccount());
					insertObj.setActuallyValue(Float.parseFloat(result.getExpectedValue()));
					insertObj.setExpectedValue(expectData.getExpectedValue());
					
					insertObj.setModifyTime("20150812");
					insertObj.setMsgType(result.getMsgType());
					insertObj.setOnbehalfOfCompId(result.getOnBehalfOfCompID());
					insertObj.setResult(passResult);
					insertObj.setTransactTime(result.getTransactTime());
					dataHandler.initDBEnvironment();
					dataHandler.insertDataToDB(insertObj);
					SocketClient.processMonitor();
					
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}finally {
					
					if(ois != null){
						try {
							ois.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(oos !=null){
						try {
							oos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (socket != null) {
						try {
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		threadPool.execute(run);

	}
	
	
	/**
	 * 关闭线程池
	 */
	public static void shutDownThreadPool(){
		threadPool.shutdown();
	}


}
