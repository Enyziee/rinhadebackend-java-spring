package com.enyziee.rinhabackend;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Pessoa {

    private UUID id;
    private String nome;
    private String apelido;
    private String nascimento;
    private String[] stack;

    public Pessoa() {
        super();
        this.id = UUID.randomUUID();
    }

    public Pessoa(UUID id, String apelido, String nome, String nascimento, String[] stack) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.nascimento = nascimento;
        this.stack = stack;
    }

    @Override
    public String toString() {
        return "Pessoa {id=" + id + ", nome=" + nome + ", apelido=" + apelido + ", nascimento=" + nascimento
                + ", stack=" + stack + "}";
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(Pessoa.this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String[] getStack() {
        return stack;
    }

    public void setStack(String[] stack) {
        this.stack = stack;
    }
}
