package com.nuuptech.demo.models.dao;

import com.nuuptech.demo.models.entity.Cliente;
import java.util.List;

public interface IClienteDao {
    
    public List<Cliente> findAll();
    
    public void save (Cliente cliente);
    
            
}