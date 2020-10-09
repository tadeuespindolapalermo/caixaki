package br.com.project.acessos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Permissao {
	
	ADMIN("ADMIN", "Administrador"),
	USER("USER","Usuário Padrão"), 
	CADASTRO_ACESSAR("CADASTRO_ACESSAR", "Cadastro - Acessar"),
    FINANCIERO_ACESSAR("FINANCIERO_ACESSAR", "Financeiro - Acessar"),
	MENSAGEM_ACESSAR("MENSAGEM_ACESSAR", "Mensagem recebida - Acessar"),
	
	/*ASSOCIAR_VENDEDORES_ACESSAR("ASSOCIAR_VENDEDORES_ACESSAR", "Associar Vendedores - Acessar"),
	ASSOCIAR_VENDEDORES_NOVO("ASSOCIAR_VENDEDORES_NOVO", "Associar Vendedores - Novo"),
	ASSOCIAR_VENDEDORES_EDITAR("ASSOCIAR_VENDEDORES_EDITAR", "Associar Vendedores - Editar"),
	ASSOCIAR_VENDEDORES_EXCLUIR("ASSOCIAR_VENDEDORES_EXCLUIR", "Associar Vendedores - Excluir"),*/
	
	BAIRRO_ACESSAR("BAIRRO_ACESSAR", "Bairro - Acessar"),
	BAIRRO_NOVO("BAIRRO_NOVO", "Bairro - Novo"),
	BAIRRO_EDITAR("BAIRRO_EDITAR", "Bairro - Editar"),
	BAIRRO_EXCLUIR("BAIRRO_EXCLUIR", "Bairro - Excluir"),
	
	BAIXA_ACESSAR("BAIXA_ACESSAR", "Baixa de título - Acessar"),
	BAIXA_NOVO("BAIXA_NOVO", "Baixa de título - Novo"),
	BAIXA_EDITAR("BAIXA_EDITAR", "Baixa de título - Editar"),
	BAIXA_EXCLUIR("BAIXA_EXCLUIR", "Baixa de título - Excluir"),
	
	
	CIDADE_ACESSAR("CIDADE_ACESSAR", "Cidade - Acessar"),
	CIDADE_NOVO("CIDADE_NOVO", "Cidade - Novo"),
	CIDADE_EDITAR("CIDADE_EDITAR", "Cidade - Editar"),
	CIDADE_EXCLUIR("CIDADE_EXCLUIR", "Cidade - Excluir"),

	CONSTRUTORA_ACESSAR("CONSTRUTORA_ACESSAR", "Construtora - Acessar"),
	CONSTRUTORA_NOVO("CONSTRUTORA_NOVO", "Construtora - Novo"),
	CONSTRUTORA_EDITAR("CONSTRUTORA_EDITAR", "Construtora - Editar"),
	CONSTRUTORA_EXCLUIR("CONSTRUTORA_EXCLUIR", "Construtora - Excluir"),
	
	COMISSAO_ACESSAR("COMISSAO_ACESSAR", "Comissão - Acessar"),
	COMISSAO_NOVO("COMISSAO_NOVO", "Comissão - Novo"),
	COMISSAO_EDITAR("COMISSAO_EDITAR", "Comissão - Editar"),
	COMISSAO_EXCLUIR("COMISSAO_EXCLUIR", "Comissão - Excluir"),
	

	CLIENTE_ACESSAR("CLIENTE_ACESSAR", "Cliente - Acessar"),
	CLIENTE_NOVO("CLIENTE_NOVO", "Cliente - Novo"),
	CLIENTE_EDITAR("CLIENTE_EDITAR", "Cliente - Editar"),
	CLIENTE_EXCLUIR("CLIENTE_EXCLUIR", "Cliente - Excluir"),
	
	EMPREENDIMENTO_ACESSAR("EMPREENDIMENTO_ACESSAR", "Empreendimento - Acessar"),
	EMPREENDIMENTO_NOVO("EMPREENDIMENTO_NOVO", "Empreendimento - Novo"),
	EMPREENDIMENTO_EDITAR("EMPREENDIMENTO_EDITAR", "Empreendimento - Editar"),
	EMPREENDIMENTO_EXCLUIR("EMPREENDIMENTO_EXCLUIR", "Empreendimento - Excluir"),
	
	ENVIAR_EMAIL("ENVIAR_EMAIL", "Enviar e-mail"),
	
	FUNCIONARIO_ACESSAR("FUNCIONARIO_ACESSAR", "Funcionário - Acessar"),
	FUNCIONARIO_NOVO("FUNCIONARIO_NOVO", "Funcionário - Novo"),
	FUNCIONARIO_EDITAR("FUNCIONARIO_EDITAR", "Funcionário - Editar"),
	FUNCIONARIO_EXCLUIR("FUNCIONARIO_EXCLUIR", "Funcionário - Excluir"), 
	
	FILIAL_ACESSAR("FILIAL_ACESSAR", "Filial - Acessar"),
	FILIAL_NOVO("FILIAL_NOVO", "Filial - Novo"),
	FILIAL_EDITAR("FILIAL_EDITAR", "Filial - Editar"),
	FILIAL_EXCLUIR("FILIAL_EXCLUIR", "Filial - Excluir"),
	
	FORNECEDOR_ACESSAR("FORNECEDOR_ACESSAR", "Fornecedor - Acessar"),
	FORNECEDOR_NOVO("FORNECEDOR_NOVO", "Fornecedor - Novo"),
	FORNECEDOR_EDITAR("FORNECEDOR_EDITAR", "Fornecedor - Editar"),
	FORNECEDOR_EXCLUIR("FORNECEDOR_EXCLUIR", "Fornecedor - Excluir"),
	

	MENSAGENS_ENVIAR_ACESSAR("MENSAGENS_ENVIAR_ACESSAR", "Enviar mensagem - Acessar"),
	MENSAGENS_ENVIAR_NOVO("MENSAGENS_ENVIAR_NOVO", "Enviar mensagem - Novo"),
	MENSAGENS_ENVIAR_EDITAR("MENSAGENS_ENVIAR_EDITAR", "Enviar mensagem - Editar"),
	MENSAGENS_ENVIAR_EXCLUIR("MENSAGENS_ENVIAR_EXCLUIR", "Enviar mensagem - Excluir"),
	
	
	PRODUTO_ACESSAR("PRODUTO_ACESSAR", "Produto - Acessar"),
	PRODUTO_NOVO("PRODUTO_NOVO", "Produto - Novo"),
	PRODUTO_EDITAR("PRODUTO_EDITAR", "Produto - Editar"),
	PRODUTO_EXCLUIR("PRODUTO_EXCLUIR", "Produto - Excluir"),
	

	SERIE_ACESSAR("SERIE_ACESSAR", "Serie - Acessar"),
	SERIE_NOVO("SERIE_NOVO", "Serie - Novo"),
	SERIE_EDITAR("SERIE_EDITAR", "Serie - Editar"),
	SERIE_EXCLUIR("SERIE_EXCLUIR", "Serie - Excluir"),
	

	TITULO_ACESSAR("TITULO_ACESSAR", "Título - Acessar"),
	TITULO_NOVO("TITULO_NOVO", "Título - Novo"),
	TITULO_EDITAR("TITULO_EDITAR", "Título - Editar"),
	TITULO_EXCLUIR("TITULO_EXCLUIR", "Título - Excluir"),
	
	
	VENDEDOR_ACESSAR("VENDEDOR_ACESSAR", "Vendedor - Acessar"),
	VENDEDOR_NOVO("VENDEDOR_NOVO", "Vendedor - Novo"),
	VENDEDOR_EDITAR("VENDEDOR_EDITAR", "Vendedor - Editar"),
	VENDEDOR_EXCLUIR("VENDEDOR_EXCLUIR", "Vendedor - Excluir"),
	
	APPLET_FILE_LOCAL("APPLET_FILE_LOCAL", "Habilitar - Applet");
	
	private String valor = "";
	private String descricao = "";

	private Permissao(String name, String descricao) {
		this.valor = name;
		this.descricao = descricao;
	}

	private Permissao() {
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return getValor();
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public static List<Permissao> getListPermissao() {
		List<Permissao> permissoes = new ArrayList<Permissao>();
		for (Permissao permissao : Permissao.values()) {
			permissoes.add(permissao);
		}
		Collections.sort(permissoes, new Comparator<Permissao>() {

			@Override
			public int compare(Permissao o1, Permissao o2) {
				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
			}
		});
		return permissoes;
	}

}
