package br.com.project.geradorcodigo;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TTextAreaDocument extends PlainDocument {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int maxLength = 0;
	private boolean isUpperCase = false;

	/**
	 * Define a quantidade máxima de caracteres a serem digitados
	 * 
	 * @param length
	 *            int
	 */
	public TTextAreaDocument(int length) {
		super();
		maxLength = length;
	}

	/**
	 * Define o documento para ser digitado apenas em uppercase
	 * 
	 * @param upper
	 *            boolean
	 */
	public void setUpperCase(boolean upper) {
		isUpperCase = upper;
	}

	/**
	 * Controla a entrada e a quantidade de caracteres
	 * 
	 * @param offs
	 *            int
	 * @param str
	 *            String
	 * @param a
	 *            AttributeSet
	 * @throws BadLocationException
	 */
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if (str != null) {
			int qtdeFinal = getLength() + str.length();

			if (maxLength == 0 || qtdeFinal <= maxLength)
				super.insertString(offs,
						(isUpperCase ? str.toUpperCase() : str), a);
			else if (qtdeFinal > maxLength) {
				String nova = str.substring(0, maxLength - getLength());
				super.insertString(offs, (isUpperCase ? nova.toUpperCase()
						: nova), a);
			}
		}
	}
}
