package com.enyziee.rinhabackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseConfiguration implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    public static final String TABLE = "pessoas";
    public static final String SCHEMA = "(id UUID PRIMARY KEY, " +
            "apelido VARCHAR(32) UNIQUE NOT NULL," +
            "nome VARCHAR(100) NOT NULL," +
            "nascimento VARCHAR(10) NOT NULL," +
            "stack VARCHAR(32)[])";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting Database init");

        String TABLE = "pessoas";
        String SCHEMA = "(id UUID PRIMARY KEY, " +
                "apelido VARCHAR(32) UNIQUE NOT NULL," +
                "nome VARCHAR(100) NOT NULL," +
                "nascimento VARCHAR(10) NOT NULL," +
                "stack VARCHAR(32)[])";

        // jdbcTemplate.execute("DROP TABLE " + TABLE);
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + TABLE + SCHEMA);

        log.info("Finished Database init");
    }
}
