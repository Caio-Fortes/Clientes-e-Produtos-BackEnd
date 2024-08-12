package com.ux.ux.repositories;

import java.util.List;
import java.util.UUID;

import com.ux.ux.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ux.ux.models.VendaModel;

@Repository
public interface VendaRepository extends JpaRepository<VendaModel, UUID>{
    List<VendaModel> findByCliente(ClienteModel cliente);
}
