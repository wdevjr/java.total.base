package beansgerenciados;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import fachada.CursoFacade;
import modelo.Curso;

@ManagedBean(name="CursoManagedBean")
@RequestScoped
public class CursoManagedBean {

   private Curso curso = new Curso();
   private CursoFacade cursoFacade = new CursoFacade();

   public CursoManagedBean() {
   }

   public Curso getCurso() {
      return curso;
   }

   public void setCurso(Curso curso) {
      this.curso = curso;
   }

   public String newCurso() {
      this.cursoFacade.create(this.curso);
      return "newSuccess";
   }

   public String editCurso() {
      this.cursoFacade.edit(this.curso);
      return "editSuccess";
   }

   public void removeCurso(ActionEvent e) {
      Integer id = (Integer) e.getComponent().getAttributes().get("codCurso");
      this.cursoFacade.remove(this.cursoFacade.find(id));
   }

   public void findCurso(ActionEvent e) {
      Integer id = (Integer) e.getComponent().getAttributes().get("codCurso");
      this.curso = this.cursoFacade.find(id);
   }

   public List<Curso> getListaCursos() {
      return this.cursoFacade.findAll();
   }

   public List<SelectItem> getCursos() {
      List<SelectItem> list = new ArrayList<SelectItem>();
      List<Curso> it = getListaCursos();
      for (int i = 0; i < it.size(); i++) {
         Curso curso = it.get(i);
         list.add(new SelectItem(curso, curso.getNome()+""));
      }
      return list;
    }
}
