package com.example.shoppy;

import com.example.shoppy.domain.Item;
import com.example.shoppy.infrastructure.ShoppingService;
import com.example.shoppy.web.HelloController;
import com.example.shoppy.web.ShoppingController;
import com.example.shoppy.web.ShoppingControllerRest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ShoppyApplicationTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webapplicationContext;

    /**
     * This will create the HelloController
     * _and_ the HellosService automagically
     */
    @Autowired
    HelloController helloController;

    @Autowired
    ShoppingController shoppingController;

    @Autowired
    ShoppingControllerRest shoppingControllerRest;


    @Autowired
    ShoppingService shoppingService;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webapplicationContext).build();
    }

    @Test
    public void testController() {
        // once autowired, you can test the controller like normal
        String results = helloController.postHello("Bob", 5);
        assertEquals("Hi Bob5", results);
    }

    @Test
    public void testShoppingController() {
        // This is our first integration test!
        // getBugs() will make a database query
        // so your db has to be configured and working to for this test to run
        List<Item> items = (List<Item>) shoppingControllerRest.getItems();
        assertEquals(15, items.size());
    }

    @Test
    public void testGetItems() throws Exception {
        Item item = new Item();
        item.setName("Cocola");
        item.setCost(BigDecimal.valueOf(34.67));

        Item item2 = new Item();
        item2.setName("Fanta");
        item2.setCost(BigDecimal.valueOf(30));
        shoppingService.saveAnItem(item2);
        shoppingService.saveAnItem(item);

        // Note all the setup above required to setup the mockMvc
        // we can easily check the status, the content type, and the view
        // to check the 'content' (the returned hmtl text) we have to use the Matchers.*
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("items"))
                .andExpect(content().string(Matchers.containsString("Cheese")))
                .andExpect(content().string(Matchers.containsString("Bread")))
                .andExpect(content().string(Matchers.containsString("Cocola")))
                .andExpect(content().string(Matchers.containsString("Fanta")))
                .andExpect(content().string(Matchers.containsString("Chicken")));

    }

}
