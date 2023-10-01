package com.enyziee.rinhabackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseConnection implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConnection.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {

        log.info("Creating tables if no exists");
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS pessoas (id VARCHAR(32) PRIMARY KEY,apelido VARCHAR(32) UNIQUE NOT NULL,nome VARCHAR(100) NOT NULL,nascimento VARCHAR(10) NOT NULL,stack VARCHAR(128));");

    }
}
