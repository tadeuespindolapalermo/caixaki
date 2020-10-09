package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.enums.TipoTitulo;
import br.com.project.enums.TituloOrigem;
import br.com.project.model.classes.Titulo;
import br.com.repository.interfaces.RepositoryTitulo;
import br.com.srv.interfaces.SrvTitulo;

@Controller
public class TituloController extends ImplementacaoCrud<Titulo> implements
		InterfaceCrud<Titulo> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvTitulo srvTitulo;
	@Resource
	private RepositoryTitulo repositoryTitulo;

	public void setSrvTitulo(SrvTitulo srvTitulo) {
		this.srvTitulo = srvTitulo;
	}

	public void setRepositoryTitulo(RepositoryTitulo repositoryTitulo) {
		this.repositoryTitulo = repositoryTitulo;
	}

	public List<SelectItem> getListTipoTitulo() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoTitulo tipoTitulo : TipoTitulo.values()) {
			items.add(new SelectItem(tipoTitulo.name(), tipoTitulo.toString()));
		}
		return items;
	}
	
	public List<SelectItem> getListTipoTituloOrigem() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TituloOrigem tipoTitulo : TituloOrigem.values()) {
			items.add(new SelectItem(tipoTitulo.name(), tipoTitulo.toString()));
		}
		return items;
	}
	
	public List<Map<String, Object>> getMediaPorOrigem(int dias) {
		return repositoryTitulo.getMediaPorOrigem(dias);
	}
	
	public List<Map<String, Object>> getMediaPorTipoReceberPagar(int dias) {
		return repositoryTitulo.getMediaPorTipoReceberPagar(dias);
	}
	
	public List<Map<String, Object>> getTitulosUltimosDias(int dias) {
		return repositoryTitulo.getTitulosUltimosDias(dias);
	}
	
	public List<Map<String, Object>> getMediaPorTipoAbertoFechado(int dias) {
		return repositoryTitulo.getMediaPorTipoAbertoFechado(dias);
	}

}
