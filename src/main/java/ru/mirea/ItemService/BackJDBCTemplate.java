package ru.mirea.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

@Component
public class BackJDBCTemplate {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BackJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Item> geItems() {
        System.out.println("jdbcTemplate");
        return jdbcTemplate.query("select * from Item", (ResultSet resultSet, int rowNum) -> {
            return new Item(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("type"), resultSet.getInt("count"), resultSet.getDouble("price"));
        });
    }

    public List<Item> deleteItem(int id) {
        try {
            jdbcTemplate.update("DELETE FROM Item WHERE id=?",id);
        }catch(DataAccessException dataAccessException){
        }
        return jdbcTemplate.query("select * from Item", (ResultSet resultSet, int rowNum) -> {
            return new Item(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("type"), resultSet.getInt("count"), resultSet.getDouble("price"));
        });
    }
}
