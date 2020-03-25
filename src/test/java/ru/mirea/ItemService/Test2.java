package ru.mirea.ItemService;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest
//@AutoConfigureMockMvc
//public class Test2 {
//    @Autowired
//    private MockMvc mockMvc;
//    @Test
//    public void shouldReturnDefaultMessage() throws Exception {
//        this.mockMvc.perform(get("/greeting"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Hello, Mock")));
//    }
//    @Test
//    public void getByIdTest() throws Exception {
//        Item item= new Item(1,"dog","pet",3,15);
//        this.mockMvc.perform(get("/getById/1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json("{'id': 1, 'name':'dog', 'type':'pet', 'count': 3,'price': 15}" )) ;
//    }
//}


@WebMvcTest
public class Test2 {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceForItems service;

    @Test
    public void findByIdTest() throws Exception {
        Item item = new Item(1, "dog", "pet", 3, 15);
        given(service.getById(1)).willReturn(item);
        this.mockMvc.perform(get("/getById/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1,'name': 'dog','type':'pet','count':3,'price': 15.0}"));
    }
    @Test
    public void findAllTest() throws Exception {
        Item item1 = new Item(1, "dog", "pet", 3, 15);
        Item item2 = new Item(2, "cat", "pet", 3, 13);
        Item item3 = new Item(3, "cat food", "stuff", 4, 10);
        Item item4 = new Item(4, "dog food", "stuff", 3, 10);
        List<Item> stocks = Arrays.asList(item1,item2,item3,item4);
        given(service.geItems()).willReturn(stocks);

        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1,'name': 'dog','type':'pet','count':3,'price': 15.0},{'id': 2,'name': 'cat','type':'pet','count':3,'price': 13.0},{'id': 3,'name': 'cat food','type':'stuff','count':4,'price': 10.0},{'id': 4,'name': 'dog food','type':'stuff','count':3,'price': 10.0}]"));
    }

    @Test
    public void putByIdTest() throws Exception {
        Item item = new Item("Polina", "people", 1, 1000);
        service.putItem(item.getName(),item.getType(),item.getCount(),item.getPrice());
        List<Item> l = service.geItems();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+l.size());
        assertEquals(item.getName(),l.get(l.size()-1).getName());
        assertEquals(item.getType(),l.get(l.size()-1).getType());
        assertEquals(item.getPrice(),l.get(l.size()-1).getPrice(), 0.0);
        assertEquals(item.getCount(),l.get(l.size()-1).getCount());

    }
}
