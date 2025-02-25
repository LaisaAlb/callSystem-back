package com.laisa.callSystem.repositories;

import com.laisa.callSystem.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
