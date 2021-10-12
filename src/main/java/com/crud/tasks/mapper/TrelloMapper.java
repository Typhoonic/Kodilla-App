package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrelloMapper {
    TrelloCardDto mapToCardDto(final TrelloCard trelloCard);
    TrelloCard mapToCard(final TrelloCardDto trelloCardDto);
    List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardsDto);
    List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards);
    @Mapping(target = "isClosed", ignore = true)
    List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto);
    @Mapping(target = "isClosed", ignore = true)
    List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists);
}
