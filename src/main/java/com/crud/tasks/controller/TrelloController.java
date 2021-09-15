package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards(){
        return trelloClient.getTrelloBoards();

       /* Optional.ofNullable(trelloBoards).get().stream()
                .filter(n -> n.getName()!=null)
                .filter(n -> n.getId()!=null)
                .filter(n -> n.getName().contains("Kodilla"))
                .forEach(System.out::println);

        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());

            System.out.println("This board contains lists: ");

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                System.out.println(trelloListDto.getName() + " - " + trelloListDto.getId() + " - " + trelloListDto.isClosed());
            });
        });*/

    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloClient.createNewTask(trelloCardDto);
    }

}
