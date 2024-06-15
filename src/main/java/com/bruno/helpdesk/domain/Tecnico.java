package com.bruno.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import com.bruno.helpdesk.domain.enums.Perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa { /* Essa classe tecnico extende da classe pessoa */
	
	
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<>();  /* Pegando a lista da classe chamado */

	/* Construtor da super classe */
	public Tecnico() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}

	/* get e set */
	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	
	

}
