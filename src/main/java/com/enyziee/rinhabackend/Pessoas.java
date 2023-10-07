package com.enyziee.rinhabackend;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Pessoas {

    @Id
    public UUID id;
    public String nome;
    public String apelido;
    public String nascimento;
    public String[] stack;

    public Pessoas() {

    }

    public Pessoas(String apelido, String nome, String nascimento, String[] stack) {
        this.nome = nome;
        this.apelido = apelido;
        this.nascimento = nascimento;
        this.stack = stack;
    }

    public Pessoas(UUID id, String apelido, String nome, String nascimento, String[] stack) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.nascimento = nascimento;
        this.stack = stack;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(Pessoas.this);
    }
}
