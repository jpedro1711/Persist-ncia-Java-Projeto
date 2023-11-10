package dados;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.RollbackException;
import model.Cliente;

public class ClienteDAO {
	public void salvar(Cliente cliente) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		try {
			em.persist(cliente);
			em.getTransaction().commit();
		} catch (RollbackException e) {
			// TODO: handle exception
			System.out.println("Erro: cliente já existente com esse CPF ou E-mail");
		}

		em.close();
		emf.close();
	}

	public Cliente buscaPorCpf(String cpf) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class);
		query.setParameter("cpf", cpf);
		List<Cliente> clientes = query.getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println(clientes.get(0).getCpf() + " - " + clientes.get(0).getName());
		return clientes.get(0);
	}

	public void findAll() {
		// READ
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Query query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
		List<Cliente> clientes = query.getResultList();

		if (clientes.isEmpty()) {
			System.out.println("Sem resultados");
		} else {
			for (Cliente c : clientes) {
				System.out.println("Nome: " + c.getName() + " / CPF: " + c.getCpf());
			}
		}

		em.getTransaction().commit();

		em.close();
		emf.close();
	}

	public void update(String cpf, Cliente data) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();
		Cliente clienteProcurado = this.buscaPorCpf(cpf);
		if (clienteProcurado != null) {
			clienteProcurado.setEmail(data.getEmail());
			clienteProcurado.setName(data.getName());
			em.getTransaction().begin();
			em.persist(clienteProcurado);
			em.getTransaction().commit();
			System.out.println("Cliente atualizado com sucesso");
		}
		em.close();
		emf.close();
	}

	public void delete(String cpf) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicosPU");
		EntityManager em = emf.createEntityManager();

		Cliente clienteProcurado = this.buscaPorCpf(cpf);

		if (clienteProcurado != null) {
			em.getTransaction().begin();
			clienteProcurado = em.merge(clienteProcurado);
			em.remove(clienteProcurado);
			em.getTransaction().commit();
			System.out.println("Cliente removido com sucesso");
		}

		em.close();
		emf.close();
	}

}
