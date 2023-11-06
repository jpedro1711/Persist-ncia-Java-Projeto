package apresentacao;

import dados.ClienteDAO;
import model.Categoria;
import model.Cliente;
import model.Funcionario;
import negocio.CategoriaNegocio;
import negocio.ClienteNegocio;
import negocio.FuncionarioNegocio;

public class FuncionarioTeste {
	public static void main(String[] args) {
		FuncionarioNegocio negocio = new FuncionarioNegocio();
		Funcionario c = new Funcionario("func", 1000.00, "func@gmail.com", "123.123.123-12", "9999-9999");
		try {
			negocio.salvar(c);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		negocio.findAll();
		System.out.println("BUSCA POR CPF");
		negocio.findByCpf(c.getCpf());
		
		Funcionario novo = new Funcionario("func", 2000.00, "func@gmail.com", "123.123.123-12", "9999-9999");
		
		negocio.update(c.getCpf(), novo);
		
		negocio.delete(c.getCpf());
	}
}
