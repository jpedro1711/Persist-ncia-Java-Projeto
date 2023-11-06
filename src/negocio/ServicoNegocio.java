package negocio;

import java.util.List;

import dados.CategoriaDAO;
import dados.FuncionarioDAO;
import dados.ServicoDAO;
import model.Categoria;
import model.Funcionario;
import model.Servico;

public class ServicoNegocio {
private ServicoDAO dao = new ServicoDAO();
	
	public ServicoNegocio() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Servico> findAll() {
		return dao.findAll();
	}
	
	public void update(String cod, Servico data) {
		dao.update(cod, data);
	}
	
	public void delete(String cod) {
		dao.delete(cod);
	}
	
	public Servico findByCod(String cod) {
		Servico result = dao.buscaPorCod(cod);
		return result;
	}

	public void salvar(Servico servico) {
		// TODO Auto-generated method stub
		dao.salvar(servico);
	}
}
