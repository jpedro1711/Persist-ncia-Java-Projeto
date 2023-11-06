package negocio;

import dados.CategoriaDAO;
import dados.ClienteDAO;
import model.Categoria;
import model.Cliente;

public class CategoriaNegocio {
	private CategoriaDAO dao = new CategoriaDAO();
	
	public CategoriaNegocio() {
		// TODO Auto-generated constructor stub
	}
	
	public void salvar(Categoria cat) {
		dao.salvar(cat);
	}
	
	public void findAll() {
		dao.findAll();
	}
	
	public void update(String categoria, Categoria data) {
		dao.update(categoria, data);
	}
	
	public void delete(String c) {
		dao.delete(c);
	}
	
	public Categoria findByNome(String cat) {
		return dao.buscaPorNome(cat);
	}
}
