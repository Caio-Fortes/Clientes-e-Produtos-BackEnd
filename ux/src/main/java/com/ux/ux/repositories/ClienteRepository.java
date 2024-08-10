package com.ux.ux.repositories;

import java.util.UUID;
import com.ux.ux.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, UUID>{

}
