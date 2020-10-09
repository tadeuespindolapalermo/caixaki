package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Pais;

@Controller
public class PaisController extends ImplementacaoCrud<Pais> implements
		InterfaceCrud<Pais> {
	private static final long serialVersionUID = 1L;

	public List<SelectItem> getListPais() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Pais> paises = super
				.finListOrderByProperty(Pais.class, "pai_nome");
		for (Pais e : paises) {
			list.add(new SelectItem(e, e.getPai_nome()));
		}
		return list;
	}

}
