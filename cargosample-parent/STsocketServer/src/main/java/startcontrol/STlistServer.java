package startcontrol;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import dao.DataBaseMethodHandler;
import dao.DataBaseMethodHandlerImpl;
import pojo.CXFreturnResult;
import pojo.InitialDataObject;
import util.HttpClientHandler4X;

public class STlistServer {
	static int count=0;
	static int totalNum=0;
	 static int controlcount=0;

	public void start() throws Exception {
		
		DataBaseMethodHandler dataHandler = new DataBaseMethodHandlerImpl();
		dataHandler.initDBEnvironment();
		totalNum = dataHandler.selectIniCount();

		ServerSocket ss = null;
		try {
			START:
			if(controlcount == 0){
				
				HttpClient httpclient = new DefaultHttpClient();
				String url = "http://localhost:8080/STsocketClient/HandlerServlet";
				HttpGet httpget = new HttpGet(url);
				HttpResponse response = null;
				try {
					response = httpclient.execute(httpget);
					HttpEntity entity = response.getEntity();
					
					BufferedReader reader = new BufferedReader(new InputStreamReader( 
					entity.getContent(), "UTF-8")); 
					String line = null; 
					// 返回是否登录成功的内容 忽略 
					if ((line = reader.readLine()) != null) { 
						System.out.println("SocketClient 服务器返回:"+line);
						//如果调用成功则，将控制计数加1,防止第二次继续调用
						++controlcount;
					}else{
						//给予服务器调用客户端失败三次，超过三次则跑出异常退出
						if(++controlcount<4){
							Thread.sleep(1000);
							break START;
						}else{
							throw new Exception("SocketServer服务端与客户端SocketClient通讯失败");
						}
					}
					
				} catch (ClientProtocolException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			ss = new ServerSocket(10801);
			System.out.println("start to accept...");

		} catch (IOException e) {
			e.printStackTrace();
		}

		List<InitialDataObject> list = null;
		while (true) {

			Socket socket = null;
			try {
				socket = ss.accept();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			ObjectOutputStream oos = null;
			ObjectInputStream ois = null;
			try {

				// ����������
				ois = new ObjectInputStream(new BufferedInputStream(
						socket.getInputStream()));
				// ���������
				oos = new ObjectOutputStream(socket.getOutputStream());
				// ����һ������
				Object object = ois.readObject();
				list = (List<InitialDataObject>) object;
				for (int i = 0; i < list.size(); i++) {
					++count;
					InitialDataObject obj = list.get(i);
					HttpClientHandler4X client4X = new HttpClientHandler4X();
					CXFreturnResult result = client4X.getHandler(obj);
					oos.writeObject(result);
					oos.flush();
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (oos != null) {
					try {
						oos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (ois != null) {
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
			//������֮��,�˳�while trueѭ��
			System.out.println("SocketServer.totalNum = "+totalNum+" count = :"+count);
			if(count==totalNum && count!=0){
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}

		}

	}

	public static void main(String args[]) throws ClassNotFoundException,
			IOException {

		STlistServer server = new STlistServer();
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
