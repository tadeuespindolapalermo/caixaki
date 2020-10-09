package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Estado;

@Controller
public class EstadoController extends ImplementacaoCrud<Estado> implements
		InterfaceCrud<Estado> {
	private static final long serialVersionUID = 1L;

	public List<SelectItem> getListEstado() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Estado> estados = super.finListOrderByProperty(Estado.class,
				"est_nome");
		for (Estado e : estados) {
			list.add(new SelectItem(e, e.getEst_nome()));
		}
		return list;
	}

}
