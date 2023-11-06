package negocio;

import dados.ClienteDAO;
import model.Cliente;

public class ClienteNegocio {
	private ClienteDAO clienteDAO = new ClienteDAO();
	
	public ClienteNegocio() {
		// TODO Auto-generated constructor stub
	}
	
	public void salvarCliente(Cliente c, int idade) throws Exception {
		if (idade < 18) {
			throw new Exception("Cliente deve ter mais de 18 anos");
		} else {
			clienteDAO.salvar(c);
		}
	}
	
	public void findAll() {
		clienteDAO.findAll();
	}
	
	public void update(String cpf, Cliente data) {
		clienteDAO.update(cpf, data);
	}
	
	public void delete(String cpf) {
		clienteDAO.delete(cpf);
	}
	
	public Cliente findByCpf(String cpf) {
		return clienteDAO.buscaPorCpf(cpf);
	}
}
