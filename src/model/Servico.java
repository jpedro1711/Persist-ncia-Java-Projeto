package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_servico")
public class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String codigo;
	private LocalDate date;
	private String descricao;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	public Servico() {

	}

	public Servico(String descricao, Cliente cliente, Funcionario funcionario, Categoria categoria) {
		super();
		this.date = LocalDate.now();
		this.descricao = descricao;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.categoria = categoria;
		this.codigo = cliente.getName() + LocalDateTime.now();
	}

	public String getCodigo() {
		return codigo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
	    return "Servico[" +
	           "\n  id=" + id +
	           "\n  codigo='" + codigo + '\'' +
	           "\n  date=" + date +
	           "\n  descricao='" + descricao + '\'' +
	           "\n  cliente=" + cliente.getName() +
	           "\n  funcionario=" + funcionario.getNome() +
	           "\n  categoria=" + categoria.getCategoria() +
	           "\n]";
	}

	
	

}
