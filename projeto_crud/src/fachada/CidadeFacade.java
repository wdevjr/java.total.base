package fachada;

import javax.persistence.EntityManager;

import modelo.Cidade;


public class CidadeFacade extends AbstractFacade<Cidade> {
    private EntityManager em = AbstractFacade.emf.createEntityManager();

    protected EntityManager getEntityManager() {
        return em;
    }

    public CidadeFacade() {
        super(Cidade.class);
    }
    

}
