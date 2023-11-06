package apresentacao;

import java.util.List;
import java.util.Scanner;

import model.Categoria;
import model.Cliente;
import model.Funcionario;
import model.Servico;
import negocio.CategoriaNegocio;
import negocio.ClienteNegocio;
import negocio.FuncionarioNegocio;
import negocio.ServicoNegocio;

public class ServicoTeste {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		ServicoNegocio servicoNegocio = new ServicoNegocio();
		CategoriaNegocio categoriaNegocio = new CategoriaNegocio();
		ClienteNegocio clienteNegocio = new ClienteNegocio();
		FuncionarioNegocio funcionarioNegocio = new FuncionarioNegocio();
		
		int opcao = 0;
		
		while (opcao != 5) {
			System.out.println("Escolha uma opção: \n1- Listar serviços \n2- Salvar novo serviço \n3- Atualizar serviço \n4- Deletar serviço");
			opcao = sc.nextInt();
			
			switch (opcao) {
			case 1:
				List<Servico> resultList = servicoNegocio.findAll();
				
				if (resultList.size() > 0) {
					for (Servico s: resultList) {
						System.out.println(s);
					}
				} else {
					System.out.println("Sem resultados!");
				}
				
				break;
			case 2:
				sc.nextLine();
				System.out.print("Descrição: ");
				String descricao = sc.nextLine();
				System.out.println();
				
				System.out.print("CPF do cliente: ");
				String clienteCPF = sc.nextLine();
				System.out.println();
				
				System.out.print("CPF do funcionário: ");
				String funcionarioCPF = sc.nextLine();
				System.out.println();
				
				System.out.print("Digite a categoria: ");
				String categoriaNome = sc.nextLine();
				System.out.println();
				
				Cliente c = null;
				Funcionario f = null;
				Categoria cat = null;
				
				try {
					c = clienteNegocio.findByCpf(clienteCPF);
					f = funcionarioNegocio.findByCpf(funcionarioCPF);
					cat = categoriaNegocio.findByNome(categoriaNome);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Erro: cliente, funcionário ou categoria não encontrados");
				}
				
				if (c != null && f != null && cat != null) {
					Servico novo = new Servico(descricao, c, f, cat);
					
					servicoNegocio.salvar(novo);
					Servico created = servicoNegocio.findByCod(novo.getCodigo());
					if (created == null) {
						System.out.println("Falha ao salvar");
					}
					else {
						System.out.println("Serviço salvo com sucesso");
					}
				}
				break;
			case 3:
				sc.nextLine();
				System.out.print("Código do serviço: ");
				String servico = sc.nextLine();
				System.out.println();
				
				System.out.print("Descrição: ");
				String novaDescricao = sc.nextLine();
				System.out.println();
				
				System.out.print("Digite a categoria: ");
				String novaCat = sc.nextLine();
				System.out.println();
				
				System.out.print("CPF do cliente: ");
				String novoCliente = sc.nextLine();
				System.out.println();
				
				System.out.print("CPF do funcionário: ");
				funcionarioCPF = sc.nextLine();
				System.out.println();
				
				Categoria novaCategoria;
				Servico existente;
				Cliente cliente;
				
				try {
					novaCategoria = categoriaNegocio.findByNome(novaCat);
					f = funcionarioNegocio.findByCpf(funcionarioCPF);
					existente = servicoNegocio.findByCod(servico);
					cliente = clienteNegocio.findByCpf(novoCliente);
				} catch (Exception e) {
					System.out.println("erro: cliente, funcionário, serviço ou categoria não foram encontrados");
					break;
				}
				
				if (novaCategoria != null && f != null && existente != null) {
					Servico novo = new Servico(novaDescricao, cliente, f, novaCategoria);
					
					servicoNegocio.update(existente.getCodigo(), novo);
					Servico updated = servicoNegocio.findByCod(existente.getCodigo());
					if (updated == null) {
						System.out.println("Falha ao atualizar");
					}
					else {
						System.out.println("Serviço salvo com sucesso");
					}
				}
				break;
			case 4:
				sc.nextLine();
				System.out.print("Código do serviço: ");
				String codServico = sc.nextLine();
				System.out.println();
				
				Servico result;
				try {
					result = servicoNegocio.findByCod(codServico);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Serviço não encontrado");
					break;
				}
				
				
				if (result == null) {
					System.out.println("Serviço não encontrado");
				}
				else {
					servicoNegocio.delete(codServico);
					
					System.out.println("Serviço deletado");
				}
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}
		}
		
		
		sc.close();
		
		/* Categoria categoria = new Categoria("Jardinagem");
		Cliente cliente = new Cliente("joao", "111.111.151-11", "joao@gmail.com");
		Funcionario funcionario = new Funcionario("José", 1200.00, "jose@gmail.com", "000.002.123-34", "4652-1236");
		
		categoriaNegocio.salvar(categoria);
		clienteNegocio.salvarCliente(cliente, 22);
		funcionarioNegocio.salvar(funcionario);
		
		Cliente c = clienteNegocio.findByCpf(cliente.getCpf());
		Funcionario f = funcionarioNegocio.findByCpf(funcionario.getCpf());
		Categoria cat = categoriaNegocio.findByNome(categoria.getCategoria());
		
		Servico servico = new Servico("Serviço de jardinagem", c, f, cat);
		servicoNegocio.salvar(servico);
		
		Servico novo = new Servico("Serviço de desentupimento de pia", cliente, funcionario, categoria);
		Servico created = servicoNegocio.findByCod(servico.getCodigo());
		servicoNegocio.update(servico.getCodigo(), novo);
		System.out.println(created.getCodigo());
		
		servicoNegocio.findAll(); */
		
		//servicoNegocio.delete(servico.getCodigo());
		
		//servicoNegocio.findAll();
	}
}
