package com.bruno.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.helpdesk.domain.Chamado;
import com.bruno.helpdesk.domain.Cliente;
import com.bruno.helpdesk.domain.Tecnico;
import com.bruno.helpdesk.domain.enums.Perfil;
import com.bruno.helpdesk.domain.enums.Prioridade;
import com.bruno.helpdesk.domain.enums.Status;
import com.bruno.helpdesk.repositories.ChamadoRepesitory;
import com.bruno.helpdesk.repositories.ClienteRepository;
import com.bruno.helpdesk.repositories.TecnicoRepesitory;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepesitory tecnicoRepesitory;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepesitory chamadoRepesitory;

	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Bruno Henrique", "75302438100", "bruno@mail.com", "9090");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Troval", "75302465874", "Linus@mail.com", "123");
		
		Chamado c1 = new Chamado(null, Status.ANDAMENTO, Prioridade.MEDIA, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		
		tecnicoRepesitory.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepesitory.saveAll(Arrays.asList(c1));
	}
}
