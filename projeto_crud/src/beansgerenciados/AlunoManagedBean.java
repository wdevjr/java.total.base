package beansgerenciados;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import fachada.AlunoFacade;
import modelo.Aluno;

@ManagedBean(name="AlunoManagedBean")
@RequestScoped
public class AlunoManagedBean {

   private Aluno aluno = new Aluno();
   private AlunoFacade alunoFacade = new AlunoFacade();

   public AlunoManagedBean() {
   }

   public Aluno getAluno() {
      return aluno;
   }

   public void setAluno(Aluno aluno) {
      this.aluno = aluno;
   }

   public String newAluno() {
      this.alunoFacade.create(this.aluno);
      return "newSuccess";
   }

   public String editAluno() {
      this.alunoFacade.edit(this.aluno);
      return "editSuccess";
   }

   public void removeAluno(ActionEvent e) {
      String id = (String) e.getComponent().getAttributes().get("codAluno");
      this.alunoFacade.remove(this.alunoFacade.find(id));
   }

   public void findAluno(ActionEvent e) {
      String id = (String) e.getComponent().getAttributes().get("codAluno");
      this.aluno = this.alunoFacade.find(id);
   }

   public List<Aluno> getListaAlunos() {
      return this.alunoFacade.findAll();
   }

   public List<SelectItem> getAlunos() {
      List<SelectItem> list = new ArrayList<SelectItem>();
      List<Aluno> it = getListaAlunos();
      for (int i = 0; i < it.size(); i++) {
         Aluno aluno = it.get(i);
         list.add(new SelectItem(aluno, aluno.getCpf()+""));
      }
      return list;
    }
}

