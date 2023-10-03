package com.enyziee.rinhabackend;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class PessoaRowMapper implements RowMapper<Pessoa> {

    @Override
    @Nullable
    public Pessoa mapRow(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString("ID");
        String nome = rs.getString("NOME");
        String apelido = rs.getString("APELIDO");
        String nascimento = rs.getString("NASCIMENTO");
        Array stackArray = rs.getArray("STACK");
        String[] stack = (String[]) stackArray.getArray();

        // Pessoa pessoa = new Pessoa(UUID.fromString(id), nome, apelido, nascimento,
        // arr);
        return new Pessoa(UUID.fromString(id), nome, apelido, nascimento, stack);
    }
}
