package com.enyziee.rinhabackend;

import java.util.ArrayList;
import java.util.UUID;

public class Pessoa {

    private UUID id;
    private String nome;
    private String apelido;
    private String nascimento;
    private ArrayList<String> stack;

    public Pessoa() {
        super();
        this.id = UUID.randomUUID();
    }

    public Pessoa(String apelido, String nome, String nascimento) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.apelido = apelido;
        this.nascimento = nascimento;
        this.stack = null;
    }

    public Pessoa(String apelido, String nome, String nascimento, ArrayList<String> stack) {
        this.id = UUID.randomUUID();
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

    public String getId() {
        return id.toString();
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

    public String getStack() {
        return stack.toString();
    }

    public void setStack(ArrayList<String> stack) {
        this.stack = stack;
    }
}
