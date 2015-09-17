package startcontrol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import pojo.CXFreturnResult;
import pojo.ExpectedDataObject;
import pojo.InitialDataObject;
import pojo.ResultDataObject;
import util.DateFormatUtil;
import dao.DataBaseMethodHandler;
import dao.DataBaseMethodHandlerImpl;

public class STlistClient {

	// 客户端开始时间
	static long clientStartTime = System.currentTimeMillis();
	// 总共已经处理的数目
	static int tempNum = 0;
	// 需要处理的case总数目
	static int totalNum = 0;

	// 用于监控整个流程的进度
	public static void processMonitor() {
		System.out.println(" 总用时："+ (System.currentTimeMillis() - clientStartTime));
	}

	public void start() throws ClassNotFoundException {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 10801);
			socket.setSoTimeout(180000);

			// 从数据库取出所有的原生对象
			DataBaseMethodHandler dataHandler = new DataBaseMethodHandlerImpl();
			dataHandler.initDBEnvironment();
			List<InitialDataObject> list = dataHandler.fetchAll();

			// 输入对象 flush（）
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(list);
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			for (int i = 0; i < list.size(); i++) {
				// 建立输入流
				Object obj = ois.readObject();
				CXFreturnResult cxfResult = (CXFreturnResult) obj;
				String dataId = cxfResult.getDataId();

				DataBaseMethodHandler dataHandler1 = new DataBaseMethodHandlerImpl();
				dataHandler1.initDBEnvironment();
				ExpectedDataObject expectData = dataHandler1
						.getDataObjectById(dataId);

				String passResult = "2";
				if (Float.parseFloat(cxfResult.getExpectedValue()) == expectData
						.getExpectedValue()) {
					passResult = "1";
				}

				ResultDataObject insertObj = new ResultDataObject();
				insertObj.setDataId(cxfResult.getDataId());
				insertObj.setAccount(cxfResult.getAccount());
				insertObj.setActuallyValue(Float.parseFloat(cxfResult
						.getExpectedValue()));
				insertObj.setExpectedValue(expectData.getExpectedValue());
				insertObj.setModifyTime(DateFormatUtil
						.getFormatNowTime("20150812"));
				insertObj.setMsgType(cxfResult.getMsgType());
				insertObj.setOnbehalfOfCompId(cxfResult.getOnBehalfOfCompID());
				insertObj.setResult(passResult);
				insertObj.setTransactTime(cxfResult.getTransactTime());
				dataHandler.initDBEnvironment();
				dataHandler.insertDataToDB(insertObj);
			}

			STlistClient.processMonitor();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (oos != null) {
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

	public static void main(String args[]) throws ClassNotFoundException,
			IOException {

		// 启动服务端
		STlistClient server = new STlistClient();
		server.start();

	}
}
