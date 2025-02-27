package com.laisa.callSystem.services;

import com.laisa.callSystem.domain.Pessoa;
import com.laisa.callSystem.domain.Tecnico;
import com.laisa.callSystem.domain.dtos.TecnicoDTO;
import com.laisa.callSystem.repositories.PessoaRepository;
import com.laisa.callSystem.repositories.TecnicoRepository;
import com.laisa.callSystem.services.exceptions.DataIntegrityViolationException;
import com.laisa.callSystem.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll(){
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO){
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return tecnicoRepository.save(newObj);
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO){
        objDTO.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCpfEEmail(objDTO);
        oldObj = new Tecnico(objDTO);
        return tecnicoRepository.save(oldObj);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO){
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        // Esse método serve tanto para validar a criação de um novo técnico, quanto para atualizar
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }
}
