package com.laisa.callSystem.resources;

import com.laisa.callSystem.domain.Tecnico;
import com.laisa.callSystem.domain.dtos.TecnicoDTO;
import com.laisa.callSystem.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    // Entitys: Queremos representar todas as respostas http
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico obj = this.service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

}
