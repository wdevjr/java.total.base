package fachada;

import javax.persistence.EntityManager;
import modelo.Disciplina;

public class DisciplinaFacade extends AbstractFacade<Disciplina> {
    private EntityManager em = AbstractFacade.emf.createEntityManager();

    protected EntityManager getEntityManager() {
        return em;
    }

    public DisciplinaFacade() {
        super(Disciplina.class);
    }
}

