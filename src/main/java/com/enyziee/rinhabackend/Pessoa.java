package com.enyziee.rinhabackend;

import java.util.List;
import java.util.UUID;

public class Pessoa {
    private UUID id;
    private String nome;
    private String nascimento;
    private List<String> stack;

    
    public Pessoa(UUID id, String nome, String nascimento) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public Pessoa(UUID id, String nome, String nascimento, List<String> stack) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.stack = stack;
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
    public String getNascimento() {
        return nascimento;
    }
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    public List<String> getStack() {
        return stack;
    }
    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    
}
