package fachada;

import javax.persistence.EntityManager;
import modelo.Aluno;

public class AlunoFacade extends AbstractFacade<Aluno> {
    private EntityManager em = AbstractFacade.emf.createEntityManager();

    protected EntityManager getEntityManager() {
        return em;
    }

    public AlunoFacade() {
        super(Aluno.class);
    }
}

