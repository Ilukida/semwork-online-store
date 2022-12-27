package ru.itis.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.dao.UserRepository;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "select * from users where email = ?";

    //language=SQL
    private static final String SQL_INSERT = "insert into users(first_name, last_name, email, password_hash, phone_number)" +
            "values (?,?,?,?,?);";

    //language=SQL
    private final static String SQL_UPDATE_AVATAR = "update users set avatar_id = ? where id = ?";

    private JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<User> userRowMapper = (row, rowNumber) -> {
        int id = row.getInt("id");
        String firstName = row.getString("first_name");
        String lastName = row.getString("last_name");
        String email = row.getString("email");
        String hashPassword = row.getString("password_hash");
        String phoneNumber = row.getString("phone_number");
        int avatarId = row.getInt("avatar_id");

        return User.builder().
                id(id).
                firstName(firstName).
                lastName(lastName).
                email(email).
                hashPassword(hashPassword).
                phoneNumber(phoneNumber).
                avatarId(avatarId).
                build();
    };

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public User save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"id"});
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getHashPassword());
            statement.setString(5, user.getPhoneNumber());
            return statement;
        }, keyHolder);

        user.setId(keyHolder.getKey().intValue());

        return user;
    }
    @Override
    public void updateAvatarForUser(Integer userId, Integer fileId) {
        jdbcTemplate.update(SQL_UPDATE_AVATAR, fileId, userId);
    }
}
