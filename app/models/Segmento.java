package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.Model;

@Entity
@Table(name="segmento")
public class Segmento extends Model {

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="nome")
	private String nome;

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
	
	public static Model.Finder<Long, Segmento> finder = new Model.Finder<Long, Segmento>(Segmento.class);
	
	public static Segmento findByName(String name) {
		return finder.where().eq("nome", name).findUnique();
	}
	
}
