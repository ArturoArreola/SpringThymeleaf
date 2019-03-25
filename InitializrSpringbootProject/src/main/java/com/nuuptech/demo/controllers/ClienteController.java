package com.nuuptech.demo.controllers;

import com.nuuptech.demo.models.dao.IClienteDao;
import com.nuuptech.demo.models.entity.Cliente;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClienteController {
    
    @Autowired
    private IClienteDao clienteDao;
    
    @RequestMapping(value="/listar", method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo", "Lista de clientes");
        model.addAttribute("clientes", clienteDao.findAll());
        return "listar";
    }
    
    @RequestMapping(value="/form")
    public String crear(Map<String,Object> model){
        Cliente cliente = new Cliente();
        
        model.put("titulo", "Alta de cliente");
        model.put("cliente", cliente);
        return "form";
    }
    
    @RequestMapping(value="/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("titulo", "Alta de cliente");
            return "form";
        }
        clienteDao.save(cliente);
        return "redirect:listar";
    }
}