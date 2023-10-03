package com.enyziee.rinhabackend;

import java.net.URI;
import java.sql.Types;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RinhaEndpointsController {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Autowired
    JdbcTemplate database;

    @PostMapping("/pessoas")
    public ResponseEntity<String> postMethodName(@RequestBody Pessoa pessoa) {
        try {

            if (pessoa.getStack() == null) {
                database.update("INSERT INTO pessoas(id, apelido, nome, nascimento) VALUES(?,?,?,?)",
                        pessoa.getId(), pessoa.getApelido(),
                        pessoa.getNome(), pessoa.getNascimento());

                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(URI.create("/pessoas/" + pessoa.getId()));
                return new ResponseEntity<String>(null, headers, 201);
            } else {

                if (pessoa.getStack().length != 3) {
                    throw new Exception("Stack size wrong!");
                }

                for (String stackElement : pessoa.getStack()) {
                    if (stackElement.matches("(\\d)")) {
                        throw new Exception("There numbers in!");
                    }

                    if (stackElement.length() > 32) {
                        throw new Exception("Too big!");
                    }
                }

                database.update("INSERT INTO pessoas(id, apelido, nome, nascimento, stack) VALUES(?,?,?,?,?)",
                        pessoa.getId(), pessoa.getApelido(),
                        pessoa.getNome(), pessoa.getNascimento(), pessoa.getStack());
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/pessoas/" + pessoa.getId()));

            return new ResponseEntity<String>(null, headers, 201);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<String>(null, null, 422);
        }
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<String> getPessoaById(@PathVariable("id") UUID id) {
        try {
            Pessoa pessoa = database.queryForObject("SELECT * FROM pessoas WHERE ID = ?", new PessoaRowMapper(),
                    id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            return new ResponseEntity<String>(pessoa.toJson(), headers, 200);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<String>(null, null, 404);
        }
    }

    @GetMapping("/pessoas")
    public ResponseEntity<String> searchPessoas(@RequestParam("termo") String termo) throws JsonProcessingException {
        log.info(termo);

        SqlParameterSource parameters = new MapSqlParameterSource("termo", termo);
        String sql = "SELECT * FROM pessoas WHERE to_tsvector('?') @@ to_tsquery('?')";
        String sqlBase = "SELECT * FROM pessoas";

        List<Pessoa> pessoas = database.query(sql,
                new Object[] { termo, termo },
                new String[] { Types.VARCHAR },
                new PessoaRowMapper());

        // // List<Pessoa> pessoas = database.query(sql, new PessoaRowMapper(), termo,
        // termo);

        // List<Pessoa> pessoas = database.query(sqlBase, new PessoaRowMapper());

        ObjectMapper objectMapper = new ObjectMapper();
        String pessoasJson = objectMapper.writeValueAsString(pessoas);

        return new ResponseEntity<String>(pessoasJson, null, 200);
    }

    @GetMapping("/contagem-pessoas")
    public int contagem() {
        return database.queryForObject("SELECT COUNT(ID) FROM pessoas", Integer.class);
    }
}
