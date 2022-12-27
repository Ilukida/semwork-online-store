package ru.itis.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.dao.ProductRepository;
import ru.itis.models.Helper;
import ru.itis.models.Product;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    //language=SQL
    private final static String SQL_INSERT = "insert into products(name, maker, price, description, category_id)" +
            "values (?,?,?,?,?)";

    //language=SQL
    private final static String SQL_GET_BY_CATEGORY = "select * from products where category_id = ?";

    //language=SQL
    private final static String SQL_GET = "select * from products where id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS_IN_BASKET = "select p.* from products as p left join basket as b on b.product_id = p.id where b.user_id = ?";

    //language=SQL
    private static final String SQL_INSERT_PRODUCT_IN_BASKET ="insert into basket(user_id, product_id) values (?,?)";

    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS_IN_LIKED = "select p.* from products as p left join liked_products as lp on lp.product_id = p.id where lp.user_id = ?";

    //language=SQL
    private static final String SQL_INSERT_PRODUCT_IN_LIKED ="insert into liked_products(user_id, product_id) values (?,?)";

    //language=SQL
    private final static String SQL_UPDATE_PHOTO = "update products as p set picture_id = ? where p.id = ?";

    //language=SQL
    private static final String SQL_DELETE_FROM_BASKET = "delete from basket where product_id = ? and user_id = ?";

    //language=SQL
    private static final String SQL_DELETE_FROM_LIKED = "delete from liked_products where product_id = ? and user_id = ?";

    //language=SQL
    private static final String SQL_SELECT_BASKET = "select * from basket where user_id =? and product_id = ?";

    //language=SQL
    private static final String SQL_SELECT_LIKED = "select * from liked_products where user_id =? and product_id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_ORDER = "select p.* from products as p left join products_orders as po on po.product_id = p.id where po.order_id = ?";

    //language=SQL
    private static final String SQL_DELETE_BASKET = "delete from basket where user_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Product> productRowMapper = (row, rowNumber) -> {
        int id = row.getInt("id");
        String name = row.getString("name");
        String maker = row.getString("maker");
        int price = row.getInt("price");
        int categoryId = row.getInt("category_id");
        int pictureId = row.getInt("picture_id");
        String description = row.getString("description");

        return Product.builder()
                .id(id)
                .name(name)
                .maker(maker)
                .price(price)
                .description(description)
                .categoryId(categoryId)
                .pictureId(pictureId)
                .build();
    };

    private final RowMapper<Helper> helperRowMapper = (row, rowNumber) -> {
        int userId = row.getInt("user_id");
        int productId = row.getInt("product_id");

        return Helper.builder()
                .userId(userId)
                .productId(productId)
                .build();
    };

    @Override
    public Product save(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"id"});
            statement.setString(1, product.getName());
            statement.setString(2, product.getMaker());
            statement.setInt(3, product.getPrice());
            statement.setString(4, product.getDescription());
            statement.setInt(5, product.getCategoryId());
            return statement;
        }, keyHolder);

        product.setId(keyHolder.getKey().intValue());
        return product;
    }

    @Override
    public List<Product> getProductByCategory(Integer categoryId) {
        return jdbcTemplate.query(SQL_GET_BY_CATEGORY,productRowMapper,categoryId);
    }

    @Override
    public Optional<Product> getProduct(Integer productId) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_GET, productRowMapper, productId)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> getAllProductsInBasket(Integer userId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS_IN_BASKET, productRowMapper, userId);
    }

    @Override
    public void saveProductInBasket(Integer userId, Integer productId) {
        jdbcTemplate.update(SQL_INSERT_PRODUCT_IN_BASKET, userId, productId);
    }

    @Override
    public List<Product> getAllProductsFormLiked(Integer userId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS_IN_LIKED, productRowMapper, userId);
    }

    @Override
    public void saveProductInLiked(Integer userId, Integer productId) {
        jdbcTemplate.update(SQL_INSERT_PRODUCT_IN_LIKED, userId, productId);
    }

    @Override
    public void addPhotoForProduct(Integer productId, Integer fileId) {
        jdbcTemplate.update(SQL_UPDATE_PHOTO, fileId, productId);
    }

    @Override
    public void deleteFromBasket(Integer productId, Integer userId) {
        jdbcTemplate.update(SQL_DELETE_FROM_BASKET, productId, userId);
    }

    @Override
    public void deleteFromLiked(Integer productId, Integer userId) {
        jdbcTemplate.update(SQL_DELETE_FROM_LIKED, productId, userId);
    }

    @Override
    public Optional<Helper> findInBasket(Integer productId, Integer userId) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_SELECT_BASKET, helperRowMapper, userId, productId)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Helper> findInLiked(Integer productId, Integer userId) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_SELECT_LIKED, helperRowMapper, userId, productId)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> getAllProductsInOrder(Integer orderId) {
        return jdbcTemplate.query(SQL_SELECT_BY_ORDER, productRowMapper, orderId);
    }

    @Override
    public void deleteAllBasket(Integer userId) {
        jdbcTemplate.update(SQL_DELETE_BASKET, userId);
    }
}
