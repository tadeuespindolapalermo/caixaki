package br.com.project.geradorcodigo;

import java.io.Serializable;

import javax.swing.JCheckBox;

public class JCheckBoxGeraCodigo extends JCheckBox implements Serializable {

	private static final long serialVersionUID = -1479150756371815793L;

	public JCheckBoxGeraCodigo(String descricao) {
		super(descricao, null, false);
	}

	public boolean gerarCodigo() {
		return this.isSelected();
	}

}
