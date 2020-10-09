package br.com.srv.implementacao;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import br.com.srv.interfaces.SrvImpressaoApplet;

public class SrvImpressaoAppletImpl implements SrvImpressaoApplet {
	private static final long serialVersionUID = 1L;

	@Override
	public HashMap<Object, Object> getHashMapImpressao(String tipoImprimir,
			HttpServletRequest httpServletRequest) throws Exception {
		HashMap<Object, Object> hashMapRetorno = new HashMap<Object, Object>();
		
		hashMapRetorno.put("grafica", true);
		hashMapRetorno.put("objetoByte[]", "Teste de impressão com applet.".getBytes());
		
		//BytesUtilJalis.gerarArquivo(null, httpServletRequest.getServletContext().getRealPath("pdf"));
		
		return hashMapRetorno;
	}

}