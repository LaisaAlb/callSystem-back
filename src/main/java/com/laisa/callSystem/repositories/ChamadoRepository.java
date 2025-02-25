package com.laisa.callSystem.repositories;

import com.laisa.callSystem.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
