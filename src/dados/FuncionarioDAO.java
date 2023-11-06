package dados;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.RollbackException;
import model.Categoria;
import model.Cliente;
import model.Funcionario;

public class FuncionarioDAO {
	public void salvar(Funcionario funcionario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		try {
			em.persist(funcionario);	
			em.getTransaction().commit();
		} catch (RollbackException e) {
			// TODO: handle exception
			System.out.println("Erro: funcionario já existente");
		}


		em.close();
		emf.close();
	}
	
	public void findAll() {
	    // READ
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
	    EntityManager em = emf.createEntityManager();
	    
	    em.getTransaction().begin(); // Iniciar a transação

	    Query query = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
	    List<Funcionario> funcionarios = query.getResultList();
	    
	    if (funcionarios.isEmpty()) {
	        System.out.println("Sem resultados");
	    } else {
	        for (Funcionario c : funcionarios) {
	            System.out.println("Funcionário: " + c.getCpf() + " - " + c.getNome());
	        }
	    }
	    
	    em.getTransaction().commit(); 
	    
	    em.close();
	    emf.close();
	}
	
	public Funcionario buscaPorCPF(String cpf) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT c FROM Funcionario c WHERE c.cpf = :cpf", Funcionario.class);
		query.setParameter("cpf", cpf);
		List<Funcionario> funcionarios = query.getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println(funcionarios.get(0).getNome());
		return funcionarios.get(0);
	}

	
	public void update(String cpf, Funcionario data) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();
		Funcionario resultado = this.buscaPorCPF(cpf);
		if (resultado != null) {
			resultado.setNome(data.getNome());
			resultado.setSalario(data.getSalario());
			resultado.setTelefone(data.getTelefone());
			System.out.println("Funcionário atualizada com sucesso");
		}
		em.close();
		emf.close();
	}
	
	public void delete(String cpf) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
	    EntityManager em = emf.createEntityManager();
	    
	    Funcionario resultado = this.buscaPorCPF(cpf);
	    
	    if (resultado != null) {
	        em.getTransaction().begin();
	        resultado = em.merge(resultado); // Mesclar a entidade
	        em.remove(resultado);
	        em.getTransaction().commit();
	        System.out.println("Funcionário removido com sucesso");
	    }
	    
	    em.close();
	    emf.close();
	}
}
