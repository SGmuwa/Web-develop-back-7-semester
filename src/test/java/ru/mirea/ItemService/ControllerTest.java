package ru.mirea.ItemService;

import net.minidev.json.JSONValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService service;

    @Test
    void greetingTest() throws Exception {
        when(service.greet()).thenReturn("Hello, Mock123");
        this.mockMvc.perform(get("/greeting"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Mock123")));
    }

    @Test
    void putItemTest() throws Exception {
        final boolean[] isItemInList = {false};
        doAnswer(invocationOnMock -> isItemInList[0] = true)
                .when(service)
                .putItem("1", "2", 3, 4.0);

        this.mockMvc.perform(
                put("/")
                        .content(JSONValue.toJSONString(
                                new Item("1", "2", 3, 4.0)))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertTrue(isItemInList[0]);
    }
    @Test
    void deleteItemByIdTest() throws Exception {
        final boolean[] isItemInList = {true};
        doAnswer(invocationOnMock -> isItemInList[0] = false)
                .when(service)
                .deleteItem(1);

        this.mockMvc.perform(
                delete("/1")
                        .content(JSONValue.toJSONString(
                                new Item(1,"dog", "pet", 3, 15.0)))
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertFalse(isItemInList[0]);
    }
    @Test
    public void findByIdTest() throws Exception {
        Item item = new Item(1, "dog", "pet", 3, 15);
        given(service.getById(1)).willReturn(item);
        this.mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSONValue.toJSONString(item)));
    }

    @Test
    public void findAllTest() throws Exception {
        Item item1 = new Item(1, "dog", "pet", 3, 15);
        Item item2 = new Item(2, "cat", "pet", 3, 13);
        Item item3 = new Item(3, "cat food", "stuff", 4, 10);
        Item item4 = new Item(4, "dog food", "stuff", 3, 10);
        List<Item> itemList = Arrays.asList(item1, item2, item3, item4);
        given(service.geItems()).willReturn(itemList);

        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSONValue.toJSONString(itemList)));
    }
}
