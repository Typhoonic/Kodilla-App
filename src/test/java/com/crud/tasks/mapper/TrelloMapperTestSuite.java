package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    void testMapToCardDto(){
        //Given
        TrelloCard trelloCard = new TrelloCard("Trello Card", "Card Description", "top", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("Trello Card", trelloCardDto.getName());
        assertEquals("Card Description", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    void testMapToCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Name", "Description", "bottom", "2");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("Name", trelloCard.getName());
        assertEquals("Description", trelloCard.getDescription());
        assertEquals("bottom", trelloCard.getPos());
        assertEquals("2", trelloCard.getListId());
    }

    @Test
    void testMapToBoard(){
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "name", trelloListDto);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "name2", trelloListDto);

        List<TrelloBoardDto> trelloBoardsDtoList = new ArrayList<>();
        trelloBoardsDtoList.add(trelloBoardDto1);
        trelloBoardsDtoList.add(trelloBoardDto2);
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDtoList);
        //Then
        assertEquals(2, trelloBoards.size());
        assertEquals("name", trelloBoards.get(0).getName());
        assertEquals("2", trelloBoards.get(1).getId());
    }

    @Test
    void testMapToBoardsDto(){
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        TrelloBoard trelloBoard1 = new TrelloBoard("3", "name3", trelloList);
        TrelloBoard trelloBoard2 = new TrelloBoard("4", "name4", trelloList);

        List<TrelloBoard> trelloBoardsList = new ArrayList<>();
        trelloBoardsList.add(trelloBoard1);
        trelloBoardsList.add(trelloBoard2);
        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoardsList);
        //Then
        assertEquals(2, trelloBoardsDto.size());
        assertEquals("name3", trelloBoardsDto.get(0).getName());
        assertEquals("4", trelloBoardsDto.get(1).getId());
    }

    @Test
    void testMapToList(){
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "name", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "name2", false);

        List<TrelloListDto> trelloDtoLists = new ArrayList<>();
        trelloDtoLists.add(trelloListDto1);
        trelloDtoLists.add(trelloListDto2);
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloDtoLists);
        //Then
        assertEquals(2, trelloLists.size());
        assertEquals("name", trelloLists.get(0).getName());
        assertEquals("2", trelloLists.get(1).getId());
        assertTrue(trelloLists.get(0).isClosed());
        assertFalse(trelloLists.get(1).isClosed());
    }

    @Test
    void testMapToListDto(){
        //Given
        TrelloList trelloList1 = new TrelloList("3", "name3", false);
        TrelloList trelloList2 = new TrelloList("4", "name4", true);

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(2, trelloLists.size());
        assertEquals("name3", trelloListsDto.get(0).getName());
        assertEquals("4", trelloListsDto.get(1).getId());
        assertFalse(trelloLists.get(0).isClosed());
        assertTrue(trelloLists.get(1).isClosed());

    }
}


