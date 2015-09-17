package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DataBaseMethodHandler;
import dao.DataBaseMethodHandlerImpl;
import pojo.SocketCommunicationConfirmData;
import startcontrol.SocketClient;


public class HandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String id = request.getParameter("id");
		response.getWriter().write("hello SocketServer !");
		response.getWriter().flush();
		final DataBaseMethodHandler handler = new DataBaseMethodHandlerImpl();
		handler.initDBEnvironment();
		
		if(id !=null ||!"".equals(id)){
			final int dataId = Integer.parseInt(id);
			String updateSql = "update tb_socketcommunicationconfirm set communication_status='1' where id="+id;
			handler.updateSocketComConData(updateSql);
			new Thread(){
				@Override
				public void run() {
					
					SocketCommunicationConfirmData data = null;
					try {
						
						while(true){
								Thread.sleep(1000);
								data =handler.querySCCdata(dataId);
								String status = data.getCommunication_status();
								if(status !=null || "2".equals(status)){
									break;
								}
						}
						handler.updateSocketComConData("update tb_socketcommunicationconfirm set start_status='1' where id="+id);
						handler.closeAll();
						Thread.sleep(2000);
						SocketClient.main(null);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}.start();
			
		}
		
   	}

}
