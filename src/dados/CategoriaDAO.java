package dados;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.RollbackException;
import model.Categoria;
import model.Cliente;

public class CategoriaDAO {
	public void salvar(Categoria categoria) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		try {
			em.persist(categoria);	
			em.getTransaction().commit();
		} catch (RollbackException e) {
			// TODO: handle exception
			System.out.println("Erro: categoria já existente");
		}


		em.close();
		emf.close();
	}
	
	public void findAll() {
	    // READ
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
	    EntityManager em = emf.createEntityManager();
	    
	    em.getTransaction().begin(); 

	    Query query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
	    List<Categoria> categorias = query.getResultList();
	    
	    if (categorias.isEmpty()) {
	        System.out.println("Sem resultados");
	    } else {
	        for (Categoria c : categorias) {
	            System.out.println("Categoria: " + c.getCategoria());
	        }
	    }
	    
	    em.getTransaction().commit(); 
	    
	    em.close();
	    emf.close();
	}
	
	public Categoria buscaPorNome(String nome) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT c FROM Categoria c WHERE c.categoria = :nome", Categoria.class);
		query.setParameter("nome", nome);
		List<Categoria> categorias = query.getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println(categorias.get(0).getCategoria());
		return categorias.get(0);
	}

	
	public void update(String nome, Categoria data) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();
		Categoria categoriaProcurada = this.buscaPorNome(nome);
		if (categoriaProcurada != null) {
			categoriaProcurada.setCategoria(data.getCategoria());	
			em.getTransaction().begin();
			em.persist(categoriaProcurada);	
			em.getTransaction().commit();
			System.out.println("Categoria atualizada com sucesso");
		}
		em.close();
		emf.close();
	}
	
	public void delete(String nome) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
	    EntityManager em = emf.createEntityManager();
	    
	    Categoria categoriaProcurada = this.buscaPorNome(nome);
	    
	    if (categoriaProcurada != null) {
	        em.getTransaction().begin();
	        categoriaProcurada = em.merge(categoriaProcurada);
	        em.remove(categoriaProcurada);
	        em.getTransaction().commit();
	        System.out.println("Categoria removido com sucesso");
	    }
	    
	    em.close();
	    emf.close();
	}
}
