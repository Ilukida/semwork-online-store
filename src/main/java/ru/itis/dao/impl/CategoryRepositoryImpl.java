package ru.itis.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.dao.CategoryRepository;
import ru.itis.models.Category;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from categories";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from categories as c where c.id = ?";

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Category> categoryRowMapper = (row, rowNumber) ->
            Category.builder()
                    .id(row.getInt("id"))
                    .name(row.getString("category"))
                    .pictureId(row.getInt("picture_id"))
                    .build();

    @Override
    public List<Category> getCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL, categoryRowMapper);
    }

    @Override
    public Optional<Category> getCategoryById(Integer id) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, categoryRowMapper, id)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
