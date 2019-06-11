package fachada;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.criterion.Order;

import modelo.Cidade;
import modelo.Pessoa;

public class PessoaFacade extends AbstractFacade<Pessoa> {
    private EntityManager em = AbstractFacade.emf.createEntityManager();

    protected EntityManager getEntityManager() {
        return em;
    }

    public PessoaFacade() {
        super(Pessoa.class);
       
    }


    

}

