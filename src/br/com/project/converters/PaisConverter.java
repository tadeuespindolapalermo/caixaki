package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Pais;

/**
 * Responsavel por efetuar a conversão nos combos de campos de pais
 * @author alex
 *
 */
@FacesConverter(forClass = Pais.class)
public class PaisConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L; 

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.isEmpty()) {
			return (Pais) HibernateUtil.getCurrentSession().get(Pais.class,
					new Long(value));
		} else {
			return null; 
		}
	}
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Pais c = (Pais) value;
			return c.getPai_id() != null && c.getPai_id() > 0 ? c.getPai_id().toString() : null;
		} else {
			return null;
		}
	}

}
