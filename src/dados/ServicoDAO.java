package dados;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.RollbackException;
import model.Categoria;
import model.Cliente;
import model.Servico;

public class ServicoDAO {
	public void salvar(Servico servico) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(servico);	
		em.getTransaction().commit();
		

		em.close();
		emf.close();
	}
	
	public List<Servico> findAll() {
	    // READ
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
	    EntityManager em = emf.createEntityManager();
	    
	    em.getTransaction().begin(); // Iniciar a transação

	    Query query = em.createQuery("SELECT c FROM Servico c", Servico.class);
	    List<Servico> servicos = query.getResultList();
	    
	    em.getTransaction().commit(); 
	    
	    em.close();
	    emf.close();
	    
	    if (servicos.isEmpty()) {
	        System.out.println("Sem resultados");
	        
	        return null;
	    } else {
	        return servicos;
	    }
	    
	}
	
	public Servico buscaPorCod(String cod) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT c FROM Servico c WHERE c.codigo = :cod", Servico.class);
		query.setParameter("cod", cod);
		List<Servico> servicos = query.getResultList();
		em.getTransaction().commit();
		Servico s = servicos.get(0);
		if (s == null) return null;
		return s;
	}

	
	public void update(String cod, Servico data) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();
		Servico result = this.buscaPorCod(cod);
		if (result != null) {
			result.setDescricao(data.getDescricao());
			em.getTransaction().begin();
			em.persist(result);	
			em.getTransaction().commit();
			System.out.println("Serviço atualizado com sucesso");
		}
		em.close();
		emf.close();
	}
	
	public void delete(String cod) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
	    EntityManager em = emf.createEntityManager();
	    
	    Servico resultado = this.buscaPorCod(cod);
	    
	    if (resultado != null) {
	        em.getTransaction().begin();
	        resultado = em.merge(resultado); // Mesclar a entidade
	        em.remove(resultado);
	        em.getTransaction().commit();
	    }
	    
	    em.close();
	    emf.close();
	}
}
