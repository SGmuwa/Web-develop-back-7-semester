package ru.mirea.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ItemDB {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    ItemDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void init() {
        // init db инициализация базы данных
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Item(id int NOT NULL PRIMARY KEY AUTO_INCREMENT ,name VARCHAR , type VARCHAR,count int, price DOUBLE )");

        //в базе данных цена в долларах
        //наполняем базу тоже тут
        jdbcTemplate.execute("INSERT INTO Item( name,type,count,price) VALUES ('dog', 'pet',3, 15), ('cat', 'pet',3,13),('cat food', 'stuff',4,10),('dog food', 'stuff',3,10)");


    }
}
