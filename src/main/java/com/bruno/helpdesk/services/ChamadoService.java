package com.bruno.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.helpdesk.domain.Chamado;
import com.bruno.helpdesk.repositories.ChamadoRepesitory;
import com.bruno.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepesitory repesitory;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repesitory.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado ID:" + id));
	}

	public List<Chamado> findAll() {
		return repesitory.findAll();
	}
}
