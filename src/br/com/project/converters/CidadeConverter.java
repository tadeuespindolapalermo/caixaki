package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Cidade;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.isEmpty()) {
				return (Cidade) HibernateUtil.getCurrentSession().get(
						Cidade.class, new Long(value));
		}
		return value;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Cidade c = (Cidade) value;
			return c.getCid_codigo() != null && c.getCid_codigo() > 0 ? c.getCid_codigo().toString() : null;
		} else {
			return null;
		}
	}
}
