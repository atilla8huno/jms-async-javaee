package br.com.oobj.handson;

import java.io.IOException;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/JmsController", "/jmsController", "/ServletJMS", "/servletJMS" })
public class ServletJMS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutorJMS produtor;
	
	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mensagem = request.getParameter("mensagem");
		
		try {
			for (int i = 0; i < 50; i++) {
				produtor.enviarMsgEntrada(mensagem);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("index.jsp");
	}
}
