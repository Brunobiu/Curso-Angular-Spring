package com.bruno.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bruno.helpdesk.domain.Cliente;
import com.bruno.helpdesk.domain.Pessoa;
import com.bruno.helpdesk.domain.dtos.ClienteDTO;
import com.bruno.helpdesk.repositories.ClienteRepository;
import com.bruno.helpdesk.repositories.PessoaRepesitory;
import com.bruno.helpdesk.services.exceptions.DataintegrityViolationException;
import com.bruno.helpdesk.services.exceptions.ObjectnotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepesitory pessoaRepesitory;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO obDto) {
		obDto.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEEmail(obDto);
		oldObj = new Cliente(obDto);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
			Cliente obj = findById(id);
			if(obj.getChamados().size() > 0) {
				throw new DataintegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!!!");
			}
			repository.deleteById(id);
			
		}

	private void validaPorCpfEEmail(ClienteDTO objDTO) {
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
