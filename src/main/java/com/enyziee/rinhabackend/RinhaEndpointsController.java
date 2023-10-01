package com.enyziee.rinhabackend;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class RinhaEndpointsController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/pessoas")
    public String postMethodName(@RequestBody Pessoa pessoa) {
        jdbcTemplate.update("INSERT INTO pessoas(id, apelido, nome, nascimento, stack) VALUES(?,?,?,?,?)", pessoa.getId(), pessoa.getApelido(),
                pessoa.getNome(), pessoa.getNascimento(), pessoa.getStack().toString());

        return "/pessoas/" + pessoa.getId();
    }

    // @GetMapping(value = "/pessoas/{id}")
    // public Pessoa getPessoaById() {
    // // TODO: process GET request
    // return new Pessoa(null, null, null);
    // }

    // @GetMapping(value = "/pessoas")
    // public Pessoa searchPessoas(@RequestParam String param) {
    // // TODO: process GET request
    // return new Pessoa(param, param, null);
    // }

    // @GetMapping(value = "/contagem-pessoas")
    // public Pessoa contagem(@RequestParam String param) {
    // // TODO: process GET request
    // return new Pessoa(param, param, null);
    // }
}
