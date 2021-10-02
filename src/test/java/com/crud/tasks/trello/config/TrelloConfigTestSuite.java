package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloConfigTestSuite {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    void testAttributes(){
        //Given
        //When
        String trelloEndpoint = trelloConfig.getTrelloApiEndpoint();
        String trelloKey = trelloConfig.getTrelloAppKey();
        String trelloToken = trelloConfig.getTrelloAppToken();
        String trelloUsername = trelloConfig.getTrelloUsername();
        //Then
        assertEquals("https://api.trello.com/1", trelloEndpoint);
        assertEquals("6b76326f05d147a2c721bf155fff6fb9", trelloKey);
        assertEquals("13c30ea43999e0c9e09fdf394dd35e7449833bb2d1c1def285da2ee05d10132b", trelloToken);
        assertEquals("kamildawid", trelloUsername);
    }
}
