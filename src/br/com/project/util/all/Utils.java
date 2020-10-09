package br.com.project.util.all;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.util.DigestUtils;

public class Utils implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Utils() {
	}

	/**
	 * Retorna texto criptografado em MD5.
	 * 
	 * @param string
	 * @return
	 */
	public static String criptogravarSenha(String string) {
		if (string != null && string.trim() != null && !string.trim().isEmpty()) {
			String senhaCripto = DigestUtils.md5DigestAsHex(string.getBytes());
			return senhaCripto;
		}
		return "not exists";
	}

	/**
	 * Retorn true se o cnpj é valido
	 * 
	 * @param cnpj
	 * @return
	 */
	public static boolean validarCnpj(String cnpj) {
		String str_cnpj = retirarMascaraCnpj(cnpj);
		int soma = 0, dig;
		String cnpj_calc = str_cnpj.substring(0, 12);

		if (str_cnpj.length() != 14)
			return false;

		char[] chr_cnpj = str_cnpj.toCharArray();

		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);

		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		soma = 0;
		for (int i = 0; i < 5; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		return str_cnpj.equals(cnpj_calc);
	}

	/**
	 * Retorna o cnpj sem mascara
	 * 
	 * @param cnpj
	 * @return
	 */
	private static String retirarMascaraCnpj(String cnpj) {
		cnpj = retirarMascaraCpf(cnpj);
		cnpj = cnpj.replace("/", "");
		return cnpj;
	}

	/**
	 * Retorna true o cpf é válido
	 * 
	 * @param cpfModel
	 * @return
	 */
	public static boolean validacaoCpf(String cpfModel) {
		String cpf = retirarMascaraCpf(cpfModel);
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;

		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;

		for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount))
					.intValue();

			d1 = d1 + (11 - nCount) * digitoCPF;

			d2 = d2 + (12 - nCount) * digitoCPF;
		}
		;

		resto = (d1 % 11);

		if (resto < 2)
			digito1 = 0;
		else
			digito1 = 11 - resto;

		d2 += 2 * digito1;

		resto = (d2 % 11);

		if (resto < 2)
			digito2 = 0;
		else
			digito2 = 11 - resto;

		String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

		return nDigVerific.equals(nDigResult);
	}

	/**
	 * Retira pontos e traços da string
	 * 
	 * @param cpf
	 *            /cnpj
	 * @return
	 */
	private static String retirarMascaraCpf(String cpf) {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		return cpf;
	}

	/**
	 * Retorn StreamedContent pra o componente de download do primefaces.
	 * 
	 * @param file
	 * @param mimeType
	 * @param nameFile
	 * @return
	 */
	public static StreamedContent getInputStreamFile(byte[] file,
			String mimeType, String nameFile) {
		InputStream inputStream = new ByteArrayInputStream(file, 0, file.length);

		return new DefaultStreamedContent(inputStream, mimeType, nameFile);
	}

	/**
	 * Ordena lista de SelectItem pelo label com os valores retornados de um
	 * Enum para preencher selectOneMenu (ComboBox) com a lista ordenada
	 * 
	 * @param list SelectItem
	 */
	public static List<SelectItem> ordenaEnumSelectItem(List<SelectItem> list) {
		Collections.sort(list, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				SelectItem s1 = (SelectItem) o1;
				SelectItem s2 = (SelectItem) o2;
				return s1.getLabel().compareTo(s2.getLabel());
			}
		});

		return list;
	}

}
