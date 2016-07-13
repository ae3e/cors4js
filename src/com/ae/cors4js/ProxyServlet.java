package com.ae.cors4js;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProxyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String urlString="";
		StringBuffer strBuf = new StringBuffer();
		if(request.getParameter("url")!=null){

			urlString = request.getQueryString().substring(4);

			try {
				URL url = new URL(urlString);
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
				String line;

				while ((line = reader.readLine()) != null) {
					// System.out.println(line);
					strBuf.append(line);
					strBuf.append(System.lineSeparator());
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			strBuf=strBuf.append("Hello cors4js");
		}
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().print(strBuf);
	}
	
}
