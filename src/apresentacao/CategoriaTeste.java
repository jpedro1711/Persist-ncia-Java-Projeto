package apresentacao;

import dados.ClienteDAO;
import model.Categoria;
import model.Cliente;
import negocio.CategoriaNegocio;
import negocio.ClienteNegocio;

public class CategoriaTeste {
	public static void main(String[] args) {
		CategoriaNegocio negocio = new CategoriaNegocio();
		Categoria c = new Categoria("Reparos");
		try {
			negocio.salvar(c);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		negocio.findAll();
		System.out.println("BUSCA POR CATEGORIA");
		negocio.findByNome(c.getCategoria());
		
		Categoria novo = new Categoria("Jardinagem");
		
		negocio.update(c.getCategoria(), novo);
		
		negocio.delete(c.getCategoria());
	}
}
