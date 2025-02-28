package com.laisa.callSystem.services;

import com.laisa.callSystem.domain.Chamado;
import com.laisa.callSystem.domain.Cliente;
import com.laisa.callSystem.domain.Tecnico;
import com.laisa.callSystem.domain.dtos.ChamadoDTO;
import com.laisa.callSystem.domain.enums.Prioridade;
import com.laisa.callSystem.domain.enums.Status;
import com.laisa.callSystem.repositories.ChamadoRepository;
import com.laisa.callSystem.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Chamado> findAll(){
        return repository.findAll();
    }

    public Chamado create(@Valid ChamadoDTO objDTO) {
    return repository.save(newChamado(objDTO));
    }

    private Chamado newChamado(ChamadoDTO objDTO) {
        Tecnico tecnico = tecnicoService.findById(objDTO.getTecnico());
        Cliente cliente = clienteService.findById(objDTO.getCliente());
        Chamado chamado = new Chamado();
        if(objDTO.getId() != null){
            chamado.setId(objDTO.getId());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(objDTO.getStatus()));
        chamado.setTitulo(objDTO.getTitulo());
        chamado.setObservacoes(objDTO.getObservacoes());
        return chamado;
    }
}
