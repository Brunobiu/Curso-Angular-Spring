package com.bruno.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.br.CPF;

import com.bruno.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Anotação do JPA, indica que esta classe será mapeada para uma tabela no banco de dados
public abstract class Pessoa implements Serializable { // Implementa Serializable para permitir a serialização da classe

    private static final long serialVersionUID = 1L;

    // Atributos protegidos, as classes Cliente e Tecnico têm acesso a esses atributos
    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID com estratégia de identidade
    protected Integer id;

    protected String nome;

    @Column(unique = true) // Garante que este campo será único no banco de dados
    protected String cpf;

    @Column(unique = true) // Garante que este campo será único no banco de dados
    protected String email;

    protected String senha;

    @ElementCollection(fetch = FetchType.EAGER) // Carrega a coleção junto com a entidade
    @CollectionTable(name = "PERFIS") // Define a tabela associativa para a coleção
    protected Set<Integer> perfis = new HashSet<>(); // Armazena os códigos dos perfis do usuário

    @JsonFormat(pattern = "dd/MM/yyyy") // Formata a data de criação
    protected LocalDate dataCriacao = LocalDate.now();

    // Construtor padrão
    public Pessoa() {
        super();
        addPerfil(Perfil.CLIENTE); // Todo usuário começa com o perfil de cliente
    }

    // Construtor com parâmetros
    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.CLIENTE); // Todo usuário começa com o perfil de cliente
    }

    // Métodos getter e setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet()); // Converte os códigos dos perfis para enum
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo()); // Adiciona o código do perfil
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    // Override do hashCode para gerar o hash a partir do cpf e id
    @Override
    public int hashCode() {
        return Objects.hash(cpf, id);
    }

    // Override do equals para comparar objetos baseados no cpf e id
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
    }
}
