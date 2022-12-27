package ru.itis.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.dao.OrderRepository;
import ru.itis.models.Order;
import ru.itis.models.Product;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private JdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String SQL_INSERT = "insert into orders(user_id, city, street, house, apartment, " +
            "card_owner, card_number, expiration, cvv, amount) values (?,?,?,?,?,?,?,?,?,?)";

    //language=SQL
    private static final String SQL_INSERT_PRODUCTS = "insert into products_orders(product_id, order_id) values(?,?)";

    //language=SQL
    private static final String SQL_SELECT_BY_USER = "select * from orders where user_id = ?";

    private RowMapper<Order> orderRowMapper = (row, rowNumber) ->
            Order.builder()
                    .id(row.getInt("id"))
                    .userId(row.getInt("user_id"))
                    .city(row.getString("city"))
                    .street(row.getString("street"))
                    .house(row.getString("house"))
                    .apartment(row.getString("apartment"))
                    .cardOwner(row.getString("card_owner"))
                    .cartNumber(row.getString("card_number"))
                    .expiration(row.getString("expiration"))
                    .cvv(row.getString("cvv"))
                    .amount(row.getInt("amount"))
                    .build();
            ;

    @Override
    public Order save(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"id"});
            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getCity());
            statement.setString(3, order.getStreet());
            statement.setString(4, order.getHouse());
            statement.setString(5, order.getApartment());
            statement.setString(6, order.getCardOwner());
            statement.setString(7, order.getCartNumber());
            statement.setString(8, order.getExpiration());
            statement.setString(9, order.getCvv());
            statement.setInt(10, order.getAmount());
            return statement;
        }, keyHolder);

        order.setId(keyHolder.getKey().intValue());
        List<Product> productList = order.getProductList();

        for(Product product : productList) {
            jdbcTemplate.update(SQL_INSERT_PRODUCTS, product.getId(), order.getId());
        }
        return order;
    }

    @Override
    public List<Order> getAllOrdersByUser(Integer userId) {
        return jdbcTemplate.query(SQL_SELECT_BY_USER, orderRowMapper, userId);
    }

}
