package com.enyziee.rinhabackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RinhaEndpointsController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/pessoas")
    public String postMethodName(@RequestBody Pessoa pessoa) {
        try {
            jdbcTemplate.update("INSERT INTO pessoas(id, apelido, nome, nascimento, stack) VALUES(?,?,?,?,?)",
                    pessoa.getId(), pessoa.getApelido(),
                    pessoa.getNome(), pessoa.getNascimento(), pessoa.getStack().toString());

            return pessoa.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }

    // @GetMapping(value = "/pessoas/{id}")
    // public Pessoa getPessoaById() {
    // return new Pessoa(null, null, null);
    // }

    // @GetMapping(value = "/pessoas")
    // public Pessoa searchPessoas(@RequestParam String param) {
    // return new Pessoa(param, param, null);
    // }

    // @GetMapping(value = "/contagem-pessoas")
    // public Pessoa contagem(@RequestParam String param) {
    // return new Pessoa(param, param, null);
    // }
}
