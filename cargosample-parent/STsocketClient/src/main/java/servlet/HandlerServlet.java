package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import startcontrol.STlistClient;


public class HandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("hello2");
		response.getWriter().flush();
   		
   		System.out.println("******************************");
   		new Thread(){
	   		@Override
	   		public void run() {
	   			try {
	   				Thread.currentThread();
					//œ»ÀØ10√Î
	   				Thread.sleep(10000);
	   				System.out.println(System.currentTimeMillis());
	   				STlistClient.main(null);
	   			} catch (ClassNotFoundException e) {
	   				e.printStackTrace();
	   			} catch (InterruptedException e) {
	   				e.printStackTrace();
	   			} catch (IOException e) {
					e.printStackTrace();
				}
	   		}
   		}.start();
   	}

}
