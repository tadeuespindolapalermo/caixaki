package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Filial;

@FacesConverter(forClass = Filial.class)
public class FilialConverter implements Converter, Serializable {
	private static final long serialVersionUID = 1L;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.isEmpty()) {
				return (Filial) HibernateUtil.getCurrentSession().get(
						Filial.class, new Long(value));
		}
		return value;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Filial c = (Filial) value;
			return c.getFil_codigo() != null && c.getFil_codigo() > 0 ? c.getFil_codigo().toString() : null;
		} else {
			return null;
		}
	}
}
