package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloControllerTestSuite {

    @InjectMocks
    private TrelloController trelloController;

    @Mock
    private TrelloFacade trelloFacade;

    @Test
    void shouldReturnListOfTrelloBoards(){
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();

        TrelloBoardDto board1 = new TrelloBoardDto("1", "name", trelloListDtos);
        TrelloBoardDto board2 = new TrelloBoardDto("2", "name", trelloListDtos);

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(board1);
        trelloBoardDtos.add(board2);

        when(trelloFacade.fetchTrelloBoard()).thenReturn(trelloBoardDtos);
        //When
        List<TrelloBoardDto> returnedList = trelloController.getTrelloBoards();
        //Then
        assertThat(returnedList).isNotNull();
        assertEquals("1", returnedList.get(0).getId());
    }

    @Test
    void shouldCreateTrelloCardDto(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "top", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "name", "url");

        when(trelloFacade.createCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto returnedCardDto = trelloController.createTrelloCard(trelloCardDto);
        //Then
        assertThat(returnedCardDto).isNotNull();
        assertEquals("url", returnedCardDto.getShortUrl());
    }
}
