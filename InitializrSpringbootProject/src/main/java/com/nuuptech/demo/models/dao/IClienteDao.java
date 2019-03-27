package com.nuuptech.demo.models.dao;

import com.nuuptech.demo.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long>{
    
}