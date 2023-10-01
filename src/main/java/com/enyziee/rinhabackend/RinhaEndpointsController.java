package com.enyziee.rinhabackend;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class RinhaEndpointsController {
    
    @PostMapping(value="/pessoas")
    public Pessoa postMethodName(@RequestBody Pessoa entity) {
        //TODO: process POST request
        return entity;
    }

    @GetMapping(value="/pessoas/{id}")
    public Pessoa getPessoaById() {
        //TODO: process GET request
        return new Pessoa(null, null, null, null);
    }

    @GetMapping(value="/pessoas")
    public Pessoa searchPessoas(@RequestParam String param) {
        //TODO: process GET request
        return new Pessoa(null, param, param, null);
    }
    
    @GetMapping(value="/contagem-pessoas")
    public Pessoa contagem(@RequestParam String param) {
        //TODO: process GET request
        return new Pessoa(null, param, param, null);
    }
}
