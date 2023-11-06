package apresentacao;

import dados.ClienteDAO;
import model.Cliente;
import negocio.ClienteNegocio;

public class ClienteTeste {
	public static void main(String[] args) {
		Cliente cliente = new Cliente("teste", "123.123.123-55", "teste@email.com");
		ClienteNegocio negocio = new ClienteNegocio();
		
		try {
			negocio.salvarCliente(cliente, 22);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		negocio.findAll();
		System.out.println("BUSCA POR CPF");
		negocio.findByCpf(cliente.getCpf());
		Cliente data = new Cliente("teste novo nome", "123.123.123-55", "teste@email.com");
		negocio.update(cliente.getCpf(), data);
		negocio.findByCpf(cliente.getCpf());
		negocio.delete(cliente.getCpf());
		negocio.findAll();
	}
}
