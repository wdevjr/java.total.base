package fachada;

import javax.persistence.EntityManager;
import modelo.Curso;

public class CursoFacade extends AbstractFacade<Curso> {
    private EntityManager em = AbstractFacade.emf.createEntityManager();

    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoFacade() {
        super(Curso.class);
    }
}

