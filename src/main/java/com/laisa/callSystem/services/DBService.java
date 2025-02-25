package com.laisa.callSystem.services;

import com.laisa.callSystem.domain.Chamado;
import com.laisa.callSystem.domain.Cliente;
import com.laisa.callSystem.domain.Tecnico;
import com.laisa.callSystem.domain.enums.Perfil;
import com.laisa.callSystem.domain.enums.Prioridade;
import com.laisa.callSystem.domain.enums.Status;
import com.laisa.callSystem.repositories.ChamadoRepository;
import com.laisa.callSystem.repositories.ClienteRepository;
import com.laisa.callSystem.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Laisa", "12345678911", "laisa@gmail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus", "23456789122", "linus@gmail.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
