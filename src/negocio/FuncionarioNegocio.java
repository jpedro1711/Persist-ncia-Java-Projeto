package negocio;

import dados.CategoriaDAO;
import dados.FuncionarioDAO;
import model.Categoria;
import model.Funcionario;

public class FuncionarioNegocio {
private FuncionarioDAO dao = new FuncionarioDAO();
	
	public FuncionarioNegocio() {
		// TODO Auto-generated constructor stub
	}
	
	public void salvar(Funcionario data) {
		dao.salvar(data);
	}
	
	public void findAll() {
		dao.findAll();
	}
	
	public void update(String cpf, Funcionario data) {
		dao.update(cpf, data);
	}
	
	public void delete(String c) {
		dao.delete(c);
	}
	
	public Funcionario findByCpf(String cpf) {
		return dao.buscaPorCPF(cpf);
	}
}
