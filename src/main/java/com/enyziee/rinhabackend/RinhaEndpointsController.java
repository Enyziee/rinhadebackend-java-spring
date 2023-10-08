package com.enyziee.rinhabackend;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/pessoas")
public class RinhaEndpointsController {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);
    private PessoasRepository pessoasRepository;

    public RinhaEndpointsController(PessoasRepository pessoasRepository) {
        this.pessoasRepository = pessoasRepository;
    }

    @PostMapping("")
    public ResponseEntity<String> savePessoa(@RequestBody Pessoas pessoa) {

        if (pessoa.nome == null || pessoa.apelido == null || pessoa.nascimento == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (pessoa.nome.length() >= 100 || pessoa.apelido.length() >= 32) {
            return ResponseEntity.badRequest().build();
        }

        if (pessoa.nome.matches("\\d")) {
            return ResponseEntity.badRequest().build();
        }

        if (!pessoa.nascimento.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return ResponseEntity.badRequest().build();
        }

        if (pessoa.stack != null) {
            for (String stackElement : pessoa.stack) {
                if (stackElement.matches("\\d") || stackElement.length() >= 32) {
                    return ResponseEntity.badRequest().build();
                }
            }
        }

        if (pessoasRepository.existsByApelido(pessoa.apelido)) {
            return ResponseEntity.unprocessableEntity().build();
        }

        try {
            Pessoas savedPessoa = pessoasRepository.save(pessoa);
            return ResponseEntity.created(URI.create("/pessoas/" + savedPessoa.id)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoas> getPessoaById(@PathVariable("id") UUID id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }

        Optional<Pessoas> pessoa = pessoasRepository.findById(id);

        if (pessoa.isPresent()) {
            return ResponseEntity.ok(pessoa.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<String> searchPessoas(@RequestParam("t") String t) throws JsonProcessingException {
        if (t.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<Pessoas> listPessoas = pessoasRepository.searchByTerm(t);

        ObjectMapper mapper = new ObjectMapper();
        String pessoasJson = mapper.writeValueAsString(listPessoas);

        return new ResponseEntity<String>(pessoasJson, null, HttpStatus.OK);
    }

    @GetMapping("/contagem-pessoas")
    public Long contagem() {
        return pessoasRepository.count();
    }
}
