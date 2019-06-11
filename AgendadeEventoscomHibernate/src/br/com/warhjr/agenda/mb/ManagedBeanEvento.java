package br.com.warhjr.agenda.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.warhjr.agenda.dao.EventoHibernateDAO;
import br.com.warhjr.agenda.modelo.Evento;

@ManagedBean(name = "EventoManagedBean")
@ViewScoped
public class ManagedBeanEvento implements Serializable {

	private static final long serialVersionUID = -7899310419263581187L;
	private Evento evento;
	private List<Evento> listaEvento;
	private ScheduleModel eventModel;

	EventoHibernateDAO dao;

	public EventoHibernateDAO getDao() {
		return dao;
	}

	public void setDao(EventoHibernateDAO dao) {
		this.dao = dao;
	}

	public List<Evento> getListaEvento() {
		return listaEvento;
	}

	public void setListaEvento(List<Evento> listaEvento) {
		this.listaEvento = listaEvento;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public void listar() {
		System.out.println("Listar evento");
		listaEvento = dao.listar();
	}

	@PostConstruct
	public void inicializar() {

		dao = new EventoHibernateDAO();
		evento = new Evento();
		eventModel = new DefaultScheduleModel();

		try {
			listaEvento = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro na inicialização!"));
		}

		for (Evento ev : listaEvento) {
			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setEndDate(ev.getFim());
			evt.setStartDate(ev.getIncio());
			evt.setTitle(ev.getTitulo());
			evt.setData(ev.getId());
			evt.setDescription(ev.getDescricao());
			evt.setAllDay(true);
			evt.setEditable(true);

			if (ev.isStatus() == true){
				
				evt.setStyleClass("emp1");
			}else if (ev.isStatus() == false)
			{
				evt.setStyleClass("emp2");
			}
			
			eventModel.addEvent(evt);
		}

	}

	public void quandoSelecionado(SelectEvent selectEvent) {

		// List<Evento> listaEvento = dao.listar();
		ScheduleEvent auxEvent = (ScheduleEvent) selectEvent.getObject();
		for (Evento ev : listaEvento) {
			if (ev.getId() == (Long) auxEvent.getData()) {
				evento = ev;
				break;
			}
		}
	}

	public void quandoNovo(SelectEvent selectEvent) {
		ScheduleEvent event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
		evento = new Evento();
		evento.setIncio(new java.sql.Date(event.getStartDate().getTime()));
		evento.setFim(new java.sql.Date(event.getEndDate().getTime()));
	}

	public void Salvar() {
		if (evento.getId() == null) {
			if (evento.getIncio().getTime() <= evento.getFim().getTime()) {
				try {
					dao.inserir(evento);
					inicializar();
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso em Salvar!", null));
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro em Salvar!", "Erro" + e.getMessage()));
				}
				evento = new Evento();
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
						"Erro: Data inicial não pode ser menor que Final!"));
			}
		} else {
			try {
				dao.alterar(evento);
				inicializar();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro em Atualizar!", "Erro" + e.getMessage()));
			}
		}
	}

	public void excluir()
	{
		if (evento.getId() != null) {
			try {
				dao.excluir(evento);
				inicializar();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro em Excluir!", "Erro" + e.getMessage()));
			}
			evento = new Evento();	
			
		
		}
	}
	
	public void quandoMovido(ScheduleEntryMoveEvent event) {

		for (Evento ev : listaEvento) {
			if (ev.getId() == (Long) event.getScheduleEvent().getData()) {
				evento = ev;
				try {
					dao.alterar(evento);
					inicializar();
				} catch (Exception e) {
					e.printStackTrace();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro em Atualizar!", "Erro" + e.getMessage()));
				}
				break;
			}

		}
	}

	public void quandoRedimencionado(ScheduleEntryResizeEvent event) {

		for (Evento ev : listaEvento) {
			if (ev.getId() == (Long) event.getScheduleEvent().getData()) {
				evento = ev;
				try {
					dao.alterar(evento);
					inicializar();
				} catch (Exception e) {
					e.printStackTrace();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro em Atualizar!", "Erro" + e.getMessage()));
				}
				break;
			}

		}
	}
}
