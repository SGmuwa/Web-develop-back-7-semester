package ru.mirea.ItemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ControllerGreetingTest {

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
                put("/", new Item("2", "3", 4, 5.0)))
                .andExpect(status().isOk());

        assertTrue(isItemInList[0]);
    }
}