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

    List<Item> geItems() {
        return jdbcTemplate.query("select * from Item", (ResultSet resultSet, int rowNum)
                -> new Item(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("type"), resultSet.getInt("count"), resultSet.getDouble("price"))
        );
    }

    void deleteItem(int id) {
        try {
            jdbcTemplate.update("DELETE FROM Item WHERE id=?",id);
        }catch(DataAccessException dataAccessException){
        }
    }
    void putItem(String name, String type, int count, double price) {
        jdbcTemplate.update("INSERT INTO Item( name,type,count,price) VALUES (?, ?, ?, ?)", name, type, count, price);
    }
    public void putItem(String name, String type, String count, String price) {
        jdbcTemplate.update("INSERT INTO Item( name,type,count,price) VALUES (?, ?, ?, ?)", name, type, count, price);
    }
}
