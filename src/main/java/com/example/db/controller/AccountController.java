package com.example.db.controller;


import com.example.db.dto.*;
import com.example.db.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
@CrossOrigin(origins = "*")
public class AccountController {
    private final AccountService accountService;


    @GetMapping
    public ResponseEntity<?> getProfileInformation(){
        return accountService.getProfileInformation();
    }


    @PostMapping
    public ResponseEntity<?> changeNickname(@RequestBody NameRequest name) {
        return accountService.changeNickname(name.getName());
    }


    @GetMapping("/myFriends")
    public ResponseEntity<?> getFriends(){
        return accountService.getFriends();
    }


    @DeleteMapping("/myFriends")
    public ResponseEntity<?> deleteFriends(@RequestBody NameRequest name){
        return accountService.deleteFriends(name.getName());
    }

    @GetMapping("/allPerson")
    public ResponseEntity<?> getNewFriends(){return accountService.getNewFriends();}


    @PostMapping("/allPerson")
    public ResponseEntity<?> sendFriendshipRequest(@RequestBody NameRequest name){
        return accountService.sendFriendshipRequest(name.getName());
    }


    @GetMapping("/requests")
    public ResponseEntity<?> requestFriendshipRequest(){
        return accountService.requestFriendshipRequest();
    }


    @PostMapping("/requests")
    public ResponseEntity<?> acceptFriendshipRequest(@RequestBody NameRequest name){
        return accountService.acceptFriendshipRequest(name.getName());
    }


    @PostMapping("/messages")
    public ResponseEntity<?> getMessages( @RequestBody NameRequest name){
        return accountService.getMessages(name.getName());
   }


    @PostMapping("/sendMessages")
    public ResponseEntity<?> sendMessage( @RequestBody MessageRequest messageRequest){
        return accountService.sendMessage(messageRequest.getName(), messageRequest.getText());
    }


    @GetMapping("/moving/task")
    public ResponseEntity<?> getAbstractTasks() {
        return accountService.getAbstractTasks();
    }


    @PostMapping("/moving/result")
    public ResponseEntity<?> resultMoving(@RequestBody NameRequest nameRequest) {
        return accountService.resultMoving(nameRequest.getName());
    }


    @GetMapping("/incidents")
    public ResponseEntity<?> getAllOrder(){
        return accountService.getAllOrder();
    }


    @PostMapping("/incidents")
    public ResponseEntity<?> reservationOrder(@RequestBody OrderRequest reservationOrderRequest){
        return accountService.reservationOrder(reservationOrderRequest.getId(),reservationOrderRequest.getName());
    }


    @GetMapping("/questionnaire")
    public ResponseEntity<?> getSkills(){
        return accountService.getSkills();
    }


    @PostMapping("/questionnaire")
    public ResponseEntity<?> saveSkills(@RequestBody List<HeroAbilityRequest> list){
        return accountService.saveSkills(list);
    }

    @GetMapping("/skillModel")
    public ResponseEntity<?> getSkillModel(){
        return accountService.getSkillModel();
    }
}
