package com.nuuptech.demo.controllers;

import com.nuuptech.demo.models.entity.Cliente;
import com.nuuptech.demo.models.service.IClienteService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClienteController {
    
    @Autowired
    private IClienteService clienteService;
    
    @RequestMapping(value="/listar", method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo", "Lista de clientes");
        model.addAttribute("clientes", clienteService.findAll());
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
        clienteService.save(cliente);
        return "redirect:listar";
    }
    
    @RequestMapping(value="/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String,Object>model){
        Cliente cliente = null;
        if(id > 0){
            cliente = clienteService.findOne(id);
        } else {
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar cliente");
        return "form";
    }
    
    
    @RequestMapping(value="/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id){
        if(id > 0){
            clienteService.delete(id);
        }
        return "redirect:/listar";
    }
}