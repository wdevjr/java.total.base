package fachada;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.Cidade;
import modelo.Pessoa;

public abstract class AbstractFacade<T> {
    private Class<T> entityClass;
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projeto_Crud");

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
	 	 try {
		 	 getEntityManager().getTransaction().begin();
            getEntityManager().persist(entity);
			 getEntityManager().getTransaction().commit();
		 } catch (Exception e) {
			 e.printStackTrace();
	 	 }
    }

    public void edit(T entity) {
	 	 try {
		 	 getEntityManager().getTransaction().begin();
        	 getEntityManager().merge(entity);
			 getEntityManager().getTransaction().commit();
		 } catch (Exception e) {
			 e.printStackTrace();
	 	 }
    }

    public void remove(T entity) {
	 	 try {
		 	 getEntityManager().getTransaction().begin();
        	 getEntityManager().remove(getEntityManager().merge(entity));
			 getEntityManager().getTransaction().commit();
		 } catch (Exception e) {
			 e.printStackTrace();
	 	 }
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
	public List<Cidade> listCidadesBy(Object desc)
	{

		String sql = "select c from Cidade c where c.nomeCidade LIKE :nomecidade";
		TypedQuery<Cidade> query = emf.createEntityManager().createQuery(sql,Cidade.class);
		query.setParameter("nomecidade","%"+desc+"%");
		List<Cidade> lista = query.getResultList();
		
		return lista;
	}
	
	public Cidade CidadeBy(Integer desc)
	{

		String sql = "select c from Cidade c where c.id= :idcidade";
		TypedQuery<Cidade> query = emf.createEntityManager().createQuery(sql,Cidade.class);
		query.setParameter("idcidade", String.valueOf(desc));
		Cidade cidade = query.getSingleResult();
		
		
		return cidade;
	}
	
	public List<Pessoa> FindAllComNovoProcPesquisa(String nomePessoa)
	{
	 String hql="select p from Pessoa p where p.nome like :nome";
	 TypedQuery<Pessoa> query = emf.createEntityManager().createQuery(hql,Pessoa.class);
	 query.setParameter("nome", "%"+nomePessoa+"%");
	 List<Pessoa> resultlList = query.getResultList();
	 
	 return resultlList;
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}

