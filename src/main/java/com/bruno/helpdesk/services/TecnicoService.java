package com.bruno.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bruno.helpdesk.domain.Pessoa;
import com.bruno.helpdesk.domain.Tecnico;
import com.bruno.helpdesk.domain.dtos.TecnicoDTO;
import com.bruno.helpdesk.repositories.PessoaRepesitory;
import com.bruno.helpdesk.repositories.TecnicoRepesitory;
import com.bruno.helpdesk.services.exceptions.DataintegrityViolationException;
import com.bruno.helpdesk.services.exceptions.ObjectnotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepesitory repository;
	
	@Autowired
	private PessoaRepesitory pessoaRepesitory;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO obDto) {
		obDto.setId(id);
		Tecnico oldObj = findById(id);
		validaPorCpfEEmail(obDto);
		oldObj = new Tecnico(obDto);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
			Tecnico obj = findById(id);
			if(obj.getChamados().size() > 0) {
				throw new DataintegrityViolationException("Tecnico possui ordens de serviço e não pode ser deletado!!!");
			}
			repository.deleteById(id);
			
		}

	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepesitory.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataintegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoaRepesitory.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataintegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

	

}
