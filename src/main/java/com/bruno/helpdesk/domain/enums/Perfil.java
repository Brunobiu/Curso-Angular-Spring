package com.bruno.helpdesk.domain.enums;

public enum Perfil {
	
	ADMIN(0, "ROLE_ADMIN"), 
	CLIENTE(1, "ROLE_CLIENTE"), 
	TECNICO(2, "ROLE_TECNICO");
	
	private Integer codigo;
	private String descricao;
	
	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCodigo())){
				return x;
			}
		}
		
		throw new IllegalArgumentException("Perfil Inválido");
	}
	
}




/*
	Constantes
	ADMIN: Representa o perfil de administrador com código 0 e descrição "ROLE_ADMIN".
	CLIENTE: Representa o perfil de cliente com código 1 e descrição "ROLE_CLIENTE".
	TECNICO: Representa o perfil de técnico com código 2 e descrição "ROLE_TECNICO".
	
	Atributos
	codigo: Um campo do tipo Integer que armazena o código do perfil.
	descricao: Um campo do tipo String que armazena a descrição do perfil.
	
	Construtor Privado
	Define o construtor privado Perfil(Integer codigo, String descricao) para inicializar as constantes com seus respectivos códigos e descrições.
	
	Métodos
	getCodigo(): Retorna o código do perfil.
	getDescricao(): Retorna a descrição do perfil.
	toEnum(Integer cod): Converte um código numérico em um perfil correspondente. Se o código não corresponder a nenhum perfil, lança uma exceção IllegalArgumentException.
	Espero que isso ajude a visualizar a estrutura do enum Perfil e entender suas partes!
*/
