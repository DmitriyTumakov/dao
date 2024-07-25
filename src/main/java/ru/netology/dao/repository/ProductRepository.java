package ru.netology.dao.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class ProductRepository {
    private String script = read("find-product.sql");
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;


    public ProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        read("find-product.sql");
    }

    public List<String> getProductName(String name) {
//        return namedParameterJdbcTemplate.query(script,
//                new MapSqlParameterSource("name", name),
//                new RowMapper<String>() {
//                    @Override
//                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        return rs.getString("product_name");
//                    }
//                });
        return entityManager.createNativeQuery(script).setParameter("name", name).getResultList();
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
