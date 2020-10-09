package br.com.project.bean.view;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope(value = "view")
public class PhotoCamView {

	@RequestMapping(value = "**/uploadPhotoWebCam", method = RequestMethod.POST)
	public void uploadPhoto(@RequestParam String imgBase64,
			@RequestParam String user, @RequestParam String userid,
			HttpServletRequest httpServletRequest) {

		try {
			String filename = "fotoCam";
			ServletContext servletContext = (ServletContext) httpServletRequest
					.getServletContext();
			String newFileName = servletContext.getRealPath("")
					+ File.separator + "resources" + File.separator + filename
					+ ".jpg";
			/* Na variavel imgBase64 está sendo recebida a imagem do upload */
			/* Grava no banco de dados */
			/* Grava em algum caminho fisico e/ou pasta do servidor */

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
