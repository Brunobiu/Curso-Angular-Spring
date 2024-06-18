package com.bruno.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.helpdesk.domain.Tecnico;
import com.bruno.helpdesk.repositories.TecnicoRepesitory;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepesitory repesitory;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repesitory.findById(id);
		return obj.orElse(null);
	}
}
