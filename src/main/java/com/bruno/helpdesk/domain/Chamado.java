package com.bruno.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.bruno.helpdesk.domain.enums.Status;
import com.bruno.helpdesk.domain.enums.Prioridade;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Chamado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id // Indica que este campo é a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID com estratégia de identidade
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy") // Formata a data de criação
	private LocalDate dataAbertura = LocalDate.now(); /* Todo chamado aberto ja mostra a data atual */
	
	@JsonFormat(pattern = "dd/MM/yyyy") // Formata a data de criação
	private LocalDate dataFechamento;
	
	private Status prioridade;
	private Prioridade status;
	private String titulo;
	private String observacoes;
	
	/*  Obrigatorio ter esse chamado */
	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	private Tecnico tecnico;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	
	public Chamado() {
		super();
	}
	public Chamado(Integer id, Status prioridade, Prioridade status, String titulo, String observacoes, Tecnico tecnico,
			Cliente cliente) {
		super();
		this.id = id;
		this.prioridade = prioridade;
		this.status = status;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.tecnico = tecnico;
		this.cliente = cliente;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public LocalDate getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public Status getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Status prioridade) {
		this.prioridade = prioridade;
	}
	public Prioridade getStatus() {
		return status;
	}
	public void setStatus(Prioridade status) {
		this.status = status;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public Tecnico getTecnico() {
		return tecnico;
	}
	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chamado other = (Chamado) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
