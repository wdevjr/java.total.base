package beansgerenciados;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;



import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import fachada.DisciplinaFacade;
import modelo.Disciplina;

@ManagedBean(name="DisciplinaManagedBean")
@RequestScoped
public class DisciplinaManagedBean {

   private Disciplina disciplina = new Disciplina();
   private DisciplinaFacade disciplinaFacade = new DisciplinaFacade();

   public DisciplinaManagedBean() {
   }

   public Disciplina getDisciplina() {
      return disciplina;
   }

   public void setDisciplina(Disciplina disciplina) {
      this.disciplina = disciplina;
   }

   public String newDisciplina() {
      this.disciplinaFacade.create(this.disciplina);
      return "newSuccess";
   }

   public String editDisciplina() {
      this.disciplinaFacade.edit(this.disciplina);
      return "editSuccess";
   }

   public void removeDisciplina(ActionEvent e) {
      Integer id = (Integer) e.getComponent().getAttributes().get("codDisciplina");
      this.disciplinaFacade.remove(this.disciplinaFacade.find(id));
   }

   public void findDisciplina(ActionEvent e) {
      Integer id = (Integer) e.getComponent().getAttributes().get("codDisciplina");
      this.disciplina = this.disciplinaFacade.find(id);
   }

   public List<Disciplina> getListaDisciplinas() {
      return this.disciplinaFacade.findAll();
   }

   public List<SelectItem> getDisciplinas() {
      List<SelectItem> list = new ArrayList<SelectItem>();
      List<Disciplina> it = getListaDisciplinas();
      for (int i = 0; i < it.size(); i++) {
         Disciplina disciplina = it.get(i);
         list.add(new SelectItem(disciplina, disciplina.getId()+""));
      }
      return list;
    }
   

}

