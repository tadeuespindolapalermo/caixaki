package br.com.srv.interfaces;

import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface SrvImpressaoApplet extends Serializable {

	HashMap<Object, Object> getHashMapImpressao(String tipoImprimir,
			HttpServletRequest httpServletRequest) throws Exception;
}