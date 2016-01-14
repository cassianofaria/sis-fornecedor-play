package models;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.SqlRow;

import dto.FornecedorDTO;

@Entity
@Table(name="fornecedor")
public class Fornecedor extends Model  {

	@Id
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name="dt_cadastro")
	private Date dtCadastro;
	
	@Column(name="dt_atualizacao")
	private Date dtAtualizacao;
	
	@Column(name="segmento")
	@ManyToOne
	private Segmento segmento;
	
	@Column(name="ativo")
	private Boolean ativo;
	
	public static Model.Finder<Long, Fornecedor> finder = new Model.Finder<Long, Fornecedor>(Fornecedor.class);
	
	public static Fornecedor findByName(String name) {
		return finder.where().eq("nome", name).findUnique();
	}
	
	private static FornecedorDTO parse(SqlRow row) {
		FornecedorDTO fornecedor = new FornecedorDTO();
		
		fornecedor.setId(row.getLong("id"));
		fornecedor.setNome(row.getString("nome"));
		
		return fornecedor;
	}
	
	public static List<FornecedorDTO> findLight() {
		String sql = "SELECT id, nome from fornecedor";
		
		return Ebean.createSqlQuery(sql).findList().stream()
			.map(item -> parse(item))
			.collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}
	
	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}


