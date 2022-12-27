package ru.itis.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.dao.SupportRepository;
import ru.itis.models.Support;

import javax.sql.DataSource;

public class SupportRepositoryImpl implements SupportRepository {

    //language=SQL
    private static final String SQL_INSERT = "insert into support(problem, user_id) values (?,?);";

    private JdbcTemplate jdbcTemplate;

    public SupportRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Support support) {
        jdbcTemplate.update(SQL_INSERT, support.getProblem(), support.getUserId());
    }
}
