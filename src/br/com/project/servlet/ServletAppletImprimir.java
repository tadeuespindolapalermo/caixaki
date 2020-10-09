package br.com.project.servlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.srv.interfaces.SrvImpressaoApplet;

/**
 * Servelet responsavel por interceptar o pedido do applet, gerar o relatorio e
 * devolver a resposta para o applet
 * **/
@Controller
@WebServlet("**/servletAppletImprimir/imprimir")
public class ServletAppletImprimir extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SrvImpressaoApplet iServiceImpressao;

	public ServletAppletImprimir() {
		super();
	}

	@RequestMapping(value = { "**/servletAppletImprimir/imprimir" }, method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String tipoImprimir = request.getParameter("tipoImprimir");

			ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());

			// obtem relatorio e outros parametros passados
			HashMap<Object, Object> hashMap = iServiceImpressao.getHashMapImpressao(tipoImprimir, request);

			// escreve resposta para o navegador/applet
			out.writeObject(hashMap);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getServletContext().getContextPath() + "/erro/error.jsf");
		}
	}

	@RequestMapping(value = { "**/servletAppletImprimir/imprimir" }, method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
