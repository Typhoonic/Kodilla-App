package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TrelloController {

    private final TrelloService trelloService;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards(){
        return trelloService.fetchTrelloBoards();

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
        return trelloService.createdTrelloCard(trelloCardDto);
    }

}
