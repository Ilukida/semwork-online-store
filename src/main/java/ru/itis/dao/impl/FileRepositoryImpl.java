package ru.itis.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.dao.FileRepository;
import ru.itis.models.FileInfo;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Optional;

public class FileRepositoryImpl implements FileRepository {

    //language=SQL
    private final static String SQL_INSERT = "insert into file_info(original_file_name, storage_file_name, type, size) " +
            "values (?,?,?,?)";

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "select * from file_info where id = ?";

    //language=SQL
    private final static String SQL_UPDATE = "update file_info set storage_file_name = ?, original_file_name = ?, type = ?, size = ? where id = ?";

    private final JdbcTemplate jdbcTemplate;

    public FileRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<FileInfo> fileRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getInt("id"))
                    .originalFileName(row.getString("original_file_name"))
                    .storageFileName(row.getString("storage_file_name"))
                    .size(row.getInt("size"))
                    .type(row.getString("type"))
                    .build();

    @Override
    public FileInfo save(FileInfo fileInfo) {
        if(fileInfo.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, fileInfo.getOriginalFileName());
                statement.setString(2, fileInfo.getStorageFileName());
                statement.setString(3, fileInfo.getType());
                statement.setLong(4, fileInfo.getSize());
                return statement;
            }, keyHolder);
            fileInfo.setId((Integer) keyHolder.getKey());
        } else {
            jdbcTemplate.update(SQL_UPDATE,
                    fileInfo.getStorageFileName(),
                    fileInfo.getOriginalFileName(),
                    fileInfo.getType(),
                    fileInfo.getSize(),
                    fileInfo.getId()
            );
        }
        return fileInfo;
    }

    @Override
    public Optional<FileInfo> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, fileRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
