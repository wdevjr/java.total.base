package br.com.warhjr.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.warhjr.dao.MeuDao;
import br.com.warhjr.dao.MeuDaoImpl;
import br.com.warhjr.dao.ThemaHibernateDAO;
import br.com.warhjr.model.Thema;

@ManagedBean(name = "ThemaMBean")
@SessionScoped
public class ThemaMB {

	private final MeuDao dao = new MeuDaoImpl();
	private List<Thema> Themas;
	Thema thema;
	ThemaHibernateDAO auxthemaDao = new ThemaHibernateDAO();

//	@PostConstruct
//	public void init()
//	{
//		Themas = dao.consultaTodosThemas();
//	}

	public List<Thema> getListaThemas() {
		return this.auxthemaDao.listar();
	}

	public List<SelectItem> getThemas() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Thema> it = getListaThemas();
		for (int i = 0; i < it.size(); i++) {
			Thema thema = it.get(i);
			list.add(new SelectItem(thema, thema.getNomethema() + ""));
		}
		return list;
	}

	public Thema getThema() {
		return thema;
	}

	public void setThema(Thema thema) {
		this.thema = thema;
	}

}
