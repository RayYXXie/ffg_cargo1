package util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import pojo.CXFreturnResult;
import pojo.InitialDataObject;

public class ServerSocketHandlerThreadPool {

	public static final ExecutorService pool = new ThreadPoolExecutor(18, 4000, 120, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>() );
	static long serverStartTime =System.currentTimeMillis();
	
	//添加线程给线程池处理
	public static void addThreadToPoll(final Socket socket){
		
		Runnable run = new Runnable(){

			public void run() {
				
				ObjectOutputStream oos = null;
				ObjectInputStream ois = null;
				try {
					
					// 建立输入流
					ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
					//建立输出流
					oos = new ObjectOutputStream( socket.getOutputStream() );
					//读入一个对象
					Object object = ois.readObject();
					InitialDataObject dataObject = (InitialDataObject) object;

					HttpClientHandler4X client4X = new HttpClientHandler4X();
					CXFreturnResult result = client4X.getHandler(dataObject);
					
					if(result == null){
						result = new CXFreturnResult();
						result.setDataId(dataObject.getDataId());
					}
					
					oos.writeObject(result);
					oos.flush();
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}finally {
					if(oos !=null){
						try {
							oos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(ois != null){
						try {
							ois.close();
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
		pool.execute(run);
	}

	/**
	 * 关闭线程池
	 */
	public static void shutDownPool(){
		
		pool.shutdown();
	}


}
