package br.com.project.geradorcodigo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.com.dao.implementacao.DaoEntidade;
import br.com.project.bean.view.EntidadeBeanView;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Entidade;
import br.com.repository.interfaces.RepositoryEntidade;
import br.com.srv.implementacao.SrvEntidadeImpl;
import br.com.srv.interfaces.SrvEntidade;

/**
 * Tela para selecionar qual classe java será criada automaticamente Gera
 * automaticamente Classes, Repositorios, Servicos e Controllers e Daos em seu
 * determinados pacotes
 * 
 * @author alex
 * 
 */

public class GerarClasseJavaApp extends JFrame implements ActionListener,
		Serializable {
	private static final long serialVersionUID = 1L;

	private String pathJavaReplace = "build/classes";
	private String pathJava = "src";
	private String fileReplace = "file:";

	@CheckBoxGeraCodigoAnnotation(descricao = "Interface Serviço")
	private String pathSrvInterface = SrvEntidade.class.getResource("")
			.toString().replaceAll(pathJavaReplace, pathJava)
			.replaceAll(fileReplace, "");

	@CheckBoxGeraCodigoAnnotation(descricao = "Serviço Implementação")
	private String pathSrvImpl = SrvEntidadeImpl.class.getResource("")
			.toString().replaceAll(pathJavaReplace, pathJava)
			.replaceAll(fileReplace, "");

	@CheckBoxGeraCodigoAnnotation(descricao = "Repository Interface")
	private String pathRepoInter = RepositoryEntidade.class.getResource("")
			.toString().replaceAll(pathJavaReplace, pathJava)
			.replaceAll(fileReplace, "");

	@CheckBoxGeraCodigoAnnotation(descricao = "Dao Implementação")
	private String pathDaoImpl = DaoEntidade.class.getResource("").toString()
			.replaceAll(pathJavaReplace, pathJava).replaceAll(fileReplace, "");

	@CheckBoxGeraCodigoAnnotation(descricao = "Managed Bean View")
	private String pathBeanView = EntidadeBeanView.class.getResource("")
			.toString().replaceAll(pathJavaReplace, pathJava)
			.replaceAll(fileReplace, "");

	@CheckBoxGeraCodigoAnnotation(descricao = "Controller")
	private String pathController = EntidadeController.class.getResource("")
			.toString().replaceAll(pathJavaReplace, pathJava)
			.replaceAll(fileReplace, "");

	@CheckBoxGeraCodigoAnnotation(descricao = "Entidade")
	private String pathEntidade = Entidade.class.getResource("").toString()
			.replaceAll(pathJavaReplace, pathJava).replaceAll(fileReplace, "");

	private JLabel labelNomeClasse = new JLabel("Nome da classe:");
	private JTextField textFieldNomeClasse = new JTextField();

	private JButton buttonSair = new JButton("Sair");
	private JButton buttonGerar = new JButton("Gerar");

	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel panelAcao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private Map<String, Boolean> resultado = new HashMap<String, Boolean>();
	private JCheckBoxGeraCodigo checkBoxGeraCodigoTodos = new JCheckBoxGeraCodigo(
			"Marcar todos");

	private JTextArea txtMensagem = new JTextArea(new TTextAreaDocument(1000));
	private JScrollPane jScrollPane = new JScrollPane(txtMensagem);

	private void montarTela() {
		setLayout(new GridBagLayout());

		buttonSair.addActionListener(this);
		buttonGerar.addActionListener(this);

		checkBoxGeraCodigoTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				marcarDesmarcarTodos();
			}
		});

		textFieldNomeClasse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				capitalizarDescricao(e);
			}

		});

		panel.setBorder(new TitledBorder("Classes a serem gerados"));
		panelAcao.setBorder(new TitledBorder("Ações"));

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.fill = GridBagConstraints.WEST;

		GridBagConstraints gridBagConstraintsPrincipal = new GridBagConstraints();
		gridBagConstraintsPrincipal.gridx = 0;
		gridBagConstraintsPrincipal.gridy = 0;
		gridBagConstraintsPrincipal.weightx = 1;
		gridBagConstraintsPrincipal.gridwidth = 2;
		gridBagConstraintsPrincipal.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraintsPrincipal.fill = GridBagConstraints.WEST;

		posocionarComponentes(gridBagConstraints);

		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.gridy++;
		gridBagConstraints.gridx = 0;
		panel.add(new JLabel(), gridBagConstraints);

		gridBagConstraintsPrincipal.fill = GridBagConstraints.BOTH;
		add(panel, gridBagConstraintsPrincipal);
		gridBagConstraintsPrincipal.fill = GridBagConstraints.BOTH;

		gridBagConstraintsPrincipal.gridy++;
		panelAcao.add(checkBoxGeraCodigoTodos);
		panelAcao.add(buttonGerar);
		panelAcao.add(buttonSair);

		gridBagConstraintsPrincipal.fill = GridBagConstraints.HORIZONTAL;
		add(labelNomeClasse, gridBagConstraintsPrincipal);
		gridBagConstraintsPrincipal.gridx++;
		gridBagConstraintsPrincipal.gridy++;
		add(textFieldNomeClasse, gridBagConstraintsPrincipal);
		gridBagConstraintsPrincipal.fill = GridBagConstraints.BOTH;

		gridBagConstraintsPrincipal.gridx = 0;
		gridBagConstraintsPrincipal.gridy++;
		panelAcao.setMaximumSize(new Dimension(600, 30));
		add(panelAcao, gridBagConstraintsPrincipal);

		gridBagConstraintsPrincipal.gridy++;
		gridBagConstraintsPrincipal.weighty = 1; 
		gridBagConstraintsPrincipal.fill = GridBagConstraints.BOTH;
		txtMensagem.setMinimumSize(new Dimension(300, 200));
		txtMensagem.setEditable(false);
		add(jScrollPane, gridBagConstraintsPrincipal); 

		montaSaidaTextArea(txtMensagem);
		setSize(new Dimension(600, 500));
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void capitalizarDescricao(KeyEvent e) {
		try {
			String conteudo = textFieldNomeClasse.getText().trim();
			if (conteudo != null && !conteudo.isEmpty()) {
				String conteudoParte = textFieldNomeClasse.getText(1,
						conteudo.length() - 1);
				String primeiraLetra = textFieldNomeClasse.getText(0, 1);
				if (conteudoParte != null && !conteudoParte.isEmpty()) {
					primeiraLetra = primeiraLetra.toUpperCase();
					textFieldNomeClasse.setText(primeiraLetra + conteudoParte);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void posocionarComponentes(GridBagConstraints gridBagConstraints) {
		for (Field field : this.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(CheckBoxGeraCodigoAnnotation.class)) {
				if (gridBagConstraints.gridx % 2 == 0) {
					gridBagConstraints.gridx++;
					gridBagConstraints.weightx = 1;
					gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
					panel.add(new JLabel(), gridBagConstraints);
					gridBagConstraints.fill = GridBagConstraints.WEST;
					gridBagConstraints.weightx = 0;
					gridBagConstraints.gridy++;
					gridBagConstraints.gridx = 0;
				}
				JCheckBoxGeraCodigo checkBoxGeraCodigo = new JCheckBoxGeraCodigo(
						field.getAnnotation(CheckBoxGeraCodigoAnnotation.class)
								.descricao());
				checkBoxGeraCodigo.setName(field.getName());
				panel.add(checkBoxGeraCodigo, gridBagConstraints);
				gridBagConstraints.gridx++;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonSair)) {
			this.dispose();
		} else if (e.getSource().equals(buttonGerar)) {
			if (textFieldNomeClasse.getText() == null
					|| textFieldNomeClasse.getText().isEmpty()) {
				textFieldNomeClasse.transferFocus();
				return;
			}
			try {
				if (validar()) {
					gerarCodigo();
					JOptionPane.showMessageDialog(this,
							"Classes geradas com sucesso :)!");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	private void marcarDesmarcarTodos() {
		if (checkBoxGeraCodigoTodos.isSelected()) {
			for (Component component : panel.getComponents()) {
				if (component instanceof JCheckBoxGeraCodigo) {
					JCheckBoxGeraCodigo boxGeraCodigo = (JCheckBoxGeraCodigo) component;
					if (!boxGeraCodigo.gerarCodigo()) {
						boxGeraCodigo.setSelected(true);
					}
				}
			}

		} else {
			for (Component component : panel.getComponents()) {
				if (component instanceof JCheckBoxGeraCodigo) {
					JCheckBoxGeraCodigo boxGeraCodigo = (JCheckBoxGeraCodigo) component;
					if (boxGeraCodigo.gerarCodigo()) {
						boxGeraCodigo.setSelected(false);
					}
				}
			}
		}
	}

	private boolean validar() {

		resultado.clear();
		for (Component component : panel.getComponents()) {
			if (component instanceof JCheckBoxGeraCodigo) {
				JCheckBoxGeraCodigo boxGeraCodigo = (JCheckBoxGeraCodigo) component;
				if (boxGeraCodigo.gerarCodigo()) {
					resultado.put(boxGeraCodigo.getName(),
							boxGeraCodigo.gerarCodigo());
				}
			}
		}

		if (resultado.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"Selecione as classes para geração.");
			return false;
		}

		char[] conteudo = textFieldNomeClasse.getText().trim().toCharArray();
		if (conteudo.length > 0) {
			for (int i = 0; i < conteudo.length; i++) {
				if (!Character.isAlphabetic(conteudo[i])) {
					JOptionPane.showMessageDialog(this, "Caracter inválido ->"
							+ conteudo[i]);
					return false;
				}
			}
		}
		return !resultado.isEmpty();
	}

	private void gerarCodigo() throws Exception {

		for (String key : resultado.keySet()) {
			if (resultado.get(key)) {
				if (key.equals("pathSrvInterface") && resultado.get(key)) {
					gerarSrvInterface();
				} else if (key.equals("pathRepoInter") && resultado.get(key)) {
					gerarRepoInter();
				} else if (key.equals("pathSrvImpl") && resultado.get(key)) {
					gerarSrvImpl();
				} else if (key.equals("pathDaoImpl") && resultado.get(key)) {
					gerarDaoImpl();
				} else if (key.equals("pathEntidade") && resultado.get(key)) {
					gerarEntidade();
				} else if (key.equals("pathController") && resultado.get(key)) {
					gerarController();
				} else if (key.equals("pathBeanView") && resultado.get(key)) {
					gerarBeanView();
				}

			}
		}
		
		gerarDeclaracaoBean();
		
	}

	private void gerarDeclaracaoBean() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Spring: \n\n");
		builder.append("<bean id=\""+textFieldNomeClasse.getText().toLowerCase()+"Controller\" name=\""+textFieldNomeClasse.getText().toLowerCase()+"Controller\"  class=\"br.com.project.geral.controller."+textFieldNomeClasse.getText()+"Controller\" />\n");
		builder.append("<bean id=\"repository"+textFieldNomeClasse.getText()+"\" name=\"repository"+textFieldNomeClasse.getText()+"\"  class=\"br.com.dao.implementacao.Dao"+textFieldNomeClasse.getText()+"\" />\n");
		builder.append("<bean id=\"srv"+textFieldNomeClasse.getText()+"\" name=\"srv"+textFieldNomeClasse.getText()+"\"  class=\"br.com.srv.implementacao.Srv"+textFieldNomeClasse.getText()+"Impl\" />\n");
		
		builder.append("\n\n\n");
		builder.append("Hibernate: \n\n");
		builder.append("<mapping class=\"br.com.project.model.classes."+textFieldNomeClasse.getText()+"\" />");
		
		txtMensagem.setText(builder.toString());
	}

	private void gerarBeanView() throws Exception {
		StringBuilder conteudo = new StringBuilder();

		conteudo.append("package br.com.project.bean.view; \n");
		conteudo.append("\n\n");
		conteudo.append("import java.util.ArrayList;\n");
		conteudo.append("import java.util.List;\n");
		conteudo.append("\n\n");
		conteudo.append("import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;\n");
		conteudo.append("import javax.annotation.Resource;\n");
		conteudo.append("import javax.faces.bean.ManagedBean;\n");
		conteudo.append("import org.springframework.context.annotation.Scope;\n");
		conteudo.append("import br.com.framework.interfac.crud.InterfaceCrud;\n");
		conteudo.append("\n\n");
		conteudo.append("import org.springframework.stereotype.Controller;\n");
		conteudo.append("\n\n");
		conteudo.append("import br.com.project.geral.controller.")
				.append(textFieldNomeClasse.getText()).append("Controller;\n");
		conteudo.append("import br.com.project.model.classes.")
				.append(textFieldNomeClasse.getText()).append(";\n");
		conteudo.append("import br.com.project.bean.geral.BeanManagedViewAbstract;\n");
		conteudo.append("\n\n");
		conteudo.append("@Controller \n");
		conteudo.append("@ManagedBean(name = \"")
				.append(textFieldNomeClasse.getText().toLowerCase())
				.append("BeanView\")\n");
		conteudo.append("@Scope(\"session\")\n");
		conteudo.append("public class ").append(textFieldNomeClasse.getText())
				.append("BeanView extends BeanManagedViewAbstract {\n");
		conteudo.append("\n\n");
		conteudo.append("private static final long serialVersionUID = 1L;");
		conteudo.append("	private CarregamentoLazyListForObject<")
				.append(textFieldNomeClasse.getText()).append("> ")
				.append("list = new CarregamentoLazyListForObject<")
				.append(textFieldNomeClasse.getText()).append(">();\n");
		conteudo.append(" private ").append(textFieldNomeClasse.getText())
				.append(" ").append("objetoSelecionado = new ")
				.append(textFieldNomeClasse.getText()).append("();\n");
		conteudo.append("\n\n");
		conteudo.append("	@Autowired  \n");
		conteudo.append("	private ").append(textFieldNomeClasse.getText())
				.append("Controller ")
				.append(textFieldNomeClasse.getText().toLowerCase())
				.append("Controller;                               \n");
		conteudo.append("\n\n");
		conteudo.append("	public void set")
				.append(textFieldNomeClasse.getText()).append("Controller(")
				.append(textFieldNomeClasse.getText()).append("Controller ")
				.append(textFieldNomeClasse.getText().toLowerCase())
				.append("Controller) {\n");
		conteudo.append("		this.")
				.append(textFieldNomeClasse.getText().toLowerCase())
				.append("Controller = ")
				.append(textFieldNomeClasse.getText().toLowerCase())
				.append("Controller;                            \n");
		conteudo.append("	}\n");
		conteudo.append(" \n\n");
		conteudo.append("	public ").append(textFieldNomeClasse.getText())
				.append("Controller get").append(textFieldNomeClasse.getText())
				.append("Controller() {                          \n");
		conteudo.append("return ")
				.append(textFieldNomeClasse.getText().toLowerCase())
				.append("Controller;\n");
		conteudo.append("}\n");
		conteudo.append("\n\n");

		conteudo.append("@Override\n");
		conteudo.append("protected Class<?> getClassImplement() {\n");
		conteudo.append("return ").append(textFieldNomeClasse.getText())
				.append(".class; \n");
		conteudo.append("}\n");

		conteudo.append("	@Override\n");
		conteudo.append("protected InterfaceCrud<?> getController() {\n");
		conteudo.append("	return ")
				.append(textFieldNomeClasse.getText().toLowerCase())
				.append("Controller;");
		conteudo.append("}\n");

		conteudo.append("@Override\n");
		conteudo.append("public String condicaoAndParaPesquisa() throws Exception {\n");
		conteudo.append("return null;\n");
		conteudo.append("}\n");

		conteudo.append("}\n");

		File filepathBeanView = new File(pathBeanView
				+ textFieldNomeClasse.getText() + "BeanView.java");
		verificaExisteArquivoJava(filepathBeanView);

		criarArquivoJava(conteudo, filepathBeanView);

	}

	private void criarArquivoJava(StringBuilder conteudo, File filepathBeanView)
			throws IOException {
		FileWriter fileWriterBeanView = new FileWriter(filepathBeanView, true);
		fileWriterBeanView.write(conteudo.toString());
		fileWriterBeanView.flush();
		fileWriterBeanView.close();
	}

	private void gerarController() throws Exception {
		StringBuilder conteudo = new StringBuilder();
		conteudo.append("package br.com.project.geral.controller; \n\n");
		conteudo.append("import javax.annotation.Resource; \n\n");
		conteudo.append("import org.springframework.stereotype.Component;\n");
		conteudo.append("import org.springframework.stereotype.Controller;\n\n");
		conteudo.append("import br.com.framework.implementacao.crud.ImplementacaoCrud;\n");
		conteudo.append("import br.com.framework.interfac.crud.InterfaceCrud;\n");
		conteudo.append("import br.com.project.model.classes.")
				.append(textFieldNomeClasse.getText()).append(";\n");
		conteudo.append("import br.com.repository.interfaces.Repository")
				.append(textFieldNomeClasse.getText()).append(";\n");
		conteudo.append("import br.com.srv.interfaces.Srv")
				.append(textFieldNomeClasse.getText()).append(";\n\n");
		conteudo.append("@Controller\n");
		conteudo.append("public class ").append(textFieldNomeClasse.getText())
				.append("Controller extends ImplementacaoCrud<")
				.append(textFieldNomeClasse.getText())
				.append("> implements       \n");
		conteudo.append("		InterfaceCrud<")
				.append(textFieldNomeClasse.getText()).append("> {\n");
		conteudo.append("	@Autowired  \n");
		conteudo.append("	private Srv").append(textFieldNomeClasse.getText())
				.append(" srv").append(textFieldNomeClasse.getText())
				.append(";\n");
		conteudo.append("	@Autowired  \n");
		conteudo.append("	private Repository")
				.append(textFieldNomeClasse.getText()).append(" repository")
				.append(textFieldNomeClasse.getText()).append(";\n");

		conteudo.append("	public void setSrv")
				.append(textFieldNomeClasse.getText()).append("(Srv")
				.append(textFieldNomeClasse.getText()).append(" srv")
				.append(textFieldNomeClasse.getText()).append(") {\n");
		conteudo.append("		this.srv").append(textFieldNomeClasse.getText())
				.append(" = srv").append(textFieldNomeClasse.getText())
				.append(";\n");
		conteudo.append("	}\n");
		conteudo.append("    \n");
		conteudo.append("	public void setRepository")
				.append(textFieldNomeClasse.getText()).append("(Repository")
				.append(textFieldNomeClasse.getText()).append(" repository")
				.append(textFieldNomeClasse.getText())
				.append(") {          \n");
		conteudo.append("		this.repository")
				.append(textFieldNomeClasse.getText()).append(" = repository")
				.append(textFieldNomeClasse.getText()).append(";\n");
		conteudo.append("	}\n");
		conteudo.append(" \n");
		conteudo.append("}\n");

		File filepathController = new File(pathController
				+ textFieldNomeClasse.getText() + "Controller.java");
		verificaExisteArquivoJava(filepathController);

		criarArquivoJava(conteudo, filepathController);
	}

	private void verificaExisteArquivoJava(File filepathController)
			throws IOException {
		if (!filepathController.exists()) {
			filepathController.createNewFile();
		} else {
			JOptionPane.showMessageDialog(this, "Já existe "
					+ filepathController.getName());
		}
	}

	private void gerarEntidade() throws Exception {
		StringBuilder conteudo = new StringBuilder();
		conteudo.append("package br.com.project.model.classes; ");
		conteudo.append("\n ");
		conteudo.append("import java.io.Serializable; ");
		conteudo.append("\n ");
		conteudo.append("import javax.persistence.Entity; \n");
		conteudo.append("import javax.persistence.GeneratedValue; \n");
		conteudo.append("import javax.persistence.GenerationType; \n");
		conteudo.append("import javax.persistence.Id; \n");
		conteudo.append("import javax.persistence.SequenceGenerator; \n");
		conteudo.append("import javax.persistence.Table; \n");
		conteudo.append("import javax.persistence.Version; \n");
		conteudo.append("\n ");
		conteudo.append("import javax.persistence.Column;\n");
		conteudo.append("import org.hibernate.envers.Audited; ");
		conteudo.append("\n ");
		conteudo.append("@Audited \n");
		conteudo.append("@Entity \n");
		conteudo.append("@Table(name = \"");
		conteudo.append(textFieldNomeClasse.getText().toLowerCase()).append(
				"\") \n");
		conteudo.append("@SequenceGenerator(name = \"");
		conteudo.append(textFieldNomeClasse.getText().toLowerCase());
		conteudo.append("_seq\", sequenceName = \"");
		conteudo.append(textFieldNomeClasse.getText().toLowerCase());
		conteudo.append("_seq\", initialValue = 1, allocationSize = 1) \n");
		conteudo.append("public class ");
		conteudo.append(textFieldNomeClasse.getText());
		conteudo.append(" implements Serializable { \n");
		conteudo.append(" \n");
		conteudo.append("	private static final long serialVersionUID = 1L;");
		conteudo.append("\n");
		conteudo.append("	@Id \n");
		conteudo.append("	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = \"");
		conteudo.append(textFieldNomeClasse.getText().toLowerCase());
		conteudo.append("_seq\") \n");
		conteudo.append("	private Long id_");
		conteudo.append(textFieldNomeClasse.getText().toLowerCase()).append(
				"; \n");
		conteudo.append("\n");
		conteudo.append("	public Long getId_");
		conteudo.append(textFieldNomeClasse.getText().toLowerCase());
		conteudo.append("() { \n");
		conteudo.append("		return id_");
		conteudo.append(textFieldNomeClasse.getText().toLowerCase()).append(
				";  \n");
		conteudo.append("	}\n");
		conteudo.append("   \n");
		conteudo.append("	public void setId_");
		conteudo.append(textFieldNomeClasse.getText().toLowerCase());
		conteudo.append("(Long id_");
		conteudo.append(textFieldNomeClasse.getText().toLowerCase());
		conteudo.append(") { \n");
		conteudo.append(" this.id_");
		conteudo.append(textFieldNomeClasse.getText().toLowerCase());
		conteudo.append(" = id_");
		conteudo.append(textFieldNomeClasse.getText().toLowerCase()).append(
				"; \n");
		conteudo.append("	}\n");
		conteudo.append("\n");
		conteudo.append("@Version\n");
		conteudo.append("@Column(name = \"versionNum\")\n");
		conteudo.append("	private int versionNum;\n");

		conteudo.append("protected int getVersionNum() { \n");
		conteudo.append("return versionNum;\n");
		conteudo.append("}\n");

		conteudo.append("public void setVersionNum(int versionNum) {\n");
		conteudo.append("this.versionNum = versionNum;\n");
		conteudo.append("}\n");
		conteudo.append("}\n");

		File filepathEntidade = new File(pathEntidade
				+ textFieldNomeClasse.getText() + ".java");
		verificaExisteArquivoJava(filepathEntidade);

		criarArquivoJava(conteudo, filepathEntidade);

	}

	private void gerarDaoImpl() throws Exception {

		StringBuilder conteudo = new StringBuilder();

		conteudo.append("package br.com.dao.implementacao;");
		conteudo.append("\n");
		conteudo.append("\n");
		conteudo.append("import org.springframework.stereotype.Repository;");
		conteudo.append("import br.com.framework.implementacao.crud.ImplementacaoCrud;");
		conteudo.append("\n");
		conteudo.append("import br.com.project.model.classes.")
				.append(textFieldNomeClasse.getText()).append(";");
		conteudo.append("\n");
		conteudo.append("import br.com.repository.interfaces.Repository")
				.append(textFieldNomeClasse.getText()).append(";");
		conteudo.append("\n");
		conteudo.append("\n");
		conteudo.append("@Repository \n");
		conteudo.append("public class Dao")
				.append(textFieldNomeClasse.getText())
				.append(" extends ImplementacaoCrud<")
				.append(textFieldNomeClasse.getText()).append(">")
				.append(" implements Repository")
				.append(textFieldNomeClasse.getText()).append(" {");
		conteudo.append("\n");
		conteudo.append("private static final long serialVersionUID = 1L;");
		conteudo.append("}");

		File filepathDao = new File(pathDaoImpl + "Dao"
				+ textFieldNomeClasse.getText() + ".java");
		verificaExisteArquivoJava(filepathDao);

		criarArquivoJava(conteudo, filepathDao);
	}

	private void gerarRepoInter() throws Exception {

		StringBuilder conteudo = new StringBuilder();

		conteudo.append("package br.com.repository.interfaces;");
		conteudo.append("\n");
		conteudo.append("\n");
		conteudo.append("import java.io.Serializable;\n");
		conteudo.append("import org.springframework.stereotype.Repository;\n");
		conteudo.append("import org.springframework.transaction.annotation.Transactional;\n");
		conteudo.append("\n");
		conteudo.append("\n");
		conteudo.append("@Transactional\n");
		conteudo.append("@Repository");
		conteudo.append("\n");
		conteudo.append("public interface Repository")
				.append(textFieldNomeClasse.getText())
				.append(" extends Serializable {");
		conteudo.append("\n");
		conteudo.append("}");

		File filepathRepoInter = new File(pathRepoInter + "Repository"
				+ textFieldNomeClasse.getText() + ".java");
		verificaExisteArquivoJava(filepathRepoInter);

		criarArquivoJava(conteudo, filepathRepoInter);
	}

	private String gerarSrvImpl() throws Exception {

		StringBuilder conteudo = new StringBuilder();

		conteudo.append("package br.com.srv.implementacao;");
		conteudo.append("\n");
		conteudo.append("\n");
		conteudo.append("import javax.annotation.Resource;");
		conteudo.append("\n");
		conteudo.append("import br.com.repository.interfaces.Repository");
		conteudo.append(textFieldNomeClasse.getText()).append(";");
		conteudo.append("\n");
		conteudo.append("import br.com.srv.interfaces.Srv");
		conteudo.append(textFieldNomeClasse.getText()).append(";");
		conteudo.append("\n");
		conteudo.append("\n");
		conteudo.append("public class Srv")
				.append(textFieldNomeClasse.getText())
				.append("Impl implements Srv")
				.append(textFieldNomeClasse.getText()).append(" {");
		conteudo.append("\n");
		conteudo.append("private static final long serialVersionUID = 1L;");
		conteudo.append("\n");
		conteudo.append("@Autowired ");
		conteudo.append("\n");
		conteudo.append("private Repository");
		conteudo.append(textFieldNomeClasse.getText());
		conteudo.append(" repository").append(textFieldNomeClasse.getText())
				.append(";");
		conteudo.append("\n");
		conteudo.append("\n");
		conteudo.append("public void setRepository");
		conteudo.append(textFieldNomeClasse.getText());
		conteudo.append("(Repository");
		conteudo.append(textFieldNomeClasse.getText());
		conteudo.append(" repository");
		conteudo.append(textFieldNomeClasse.getText()).append(") {");
		conteudo.append("\n");
		conteudo.append("\n");
		conteudo.append("this.repository");
		conteudo.append(textFieldNomeClasse.getText()).append("= repository");
		conteudo.append(textFieldNomeClasse.getText()).append(";");
		conteudo.append("\n");
		conteudo.append("}");
		conteudo.append("\n");
		conteudo.append("}");

		File filepathSrvImpl = new File(pathSrvImpl + "Srv"
				+ textFieldNomeClasse.getText() + "Impl.java");
		verificaExisteArquivoJava(filepathSrvImpl);

		criarArquivoJava(conteudo, filepathSrvImpl);

		return filepathSrvImpl.getName();
	}

	private String gerarSrvInterface() throws Exception {
		StringBuilder conteudo = new StringBuilder();

		conteudo.append("package br.com.srv.interfaces; ");
		conteudo.append("\n");
		conteudo.append("import java.io.Serializable;");
		conteudo.append("import org.springframework.stereotype.Service;");
		conteudo.append("\n");
		conteudo.append("\n");
		conteudo.append("@Service\n");
		conteudo.append("public interface Srv");
		conteudo.append(textFieldNomeClasse.getText());
		conteudo.append(" extends Serializable {");
		conteudo.append("\n");
		conteudo.append("}");

		File fileSrvInterface = new File(pathSrvInterface + "Srv"
				+ textFieldNomeClasse.getText() + ".java");
		verificaExisteArquivoJava(fileSrvInterface);

		criarArquivoJava(conteudo, fileSrvInterface);

		return fileSrvInterface.getName();
	}

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException {
		GerarClasseJavaApp gerarClasseJava = new GerarClasseJavaApp();
		gerarClasseJava.montarTela();
	}
	
	public static void montaSaidaTextArea(final JTextArea t) {
	    t.setToolTipText("Ctrl + ENTER - próximo campo");

	    t.addKeyListener(new KeyAdapter() {
	      public void keyReleased(KeyEvent e) {
	        if (e.getKeyCode() == KeyEvent.VK_ENTER &&
	            (e.isControlDown() || t.getText().endsWith("\n\n"))) {
	        	FocusManager.getCurrentManager().focusNextComponent();
	        }      	
	      }
	    });
	  }
	  

}
