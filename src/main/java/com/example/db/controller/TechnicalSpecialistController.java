package com.example.db.controller;

import com.example.db.dto.IncidentRequest;
import com.example.db.dto.NameRequest;
import com.example.db.dto.OrderRequest;
import com.example.db.service.TechnicalSpecialistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/work")
@CrossOrigin(origins = "*")
public class TechnicalSpecialistController {
    private final TechnicalSpecialistService technicalSpecialistService;

    @GetMapping("/tasks")
    public ResponseEntity<?> getTasks(){
        return technicalSpecialistService.getTasks();
    }


    @PostMapping("/tasks")
    public ResponseEntity<?> addTask(@RequestBody NameRequest name){
        return technicalSpecialistService.addTask(name.getName());
    }


    @DeleteMapping("/tasks")
    public ResponseEntity<?> deleteTask(@RequestBody NameRequest name){
        return technicalSpecialistService.deleteTask(name.getName());
    }


    @GetMapping("/incidents")
    public ResponseEntity<?> getOngoingOrders(){
        return technicalSpecialistService.getOngoingOrders();
    }

    @GetMapping("/incidents/monsters")
    public ResponseEntity<?> getAllMonsters(){
        return technicalSpecialistService.getAllMonsters();
    }

    @GetMapping("/incidents/universals")
    public ResponseEntity<?> getAllUniversals(){
        return technicalSpecialistService.getAllUniversals();
    }

    @PostMapping("/incidents/addIncidents")
    public ResponseEntity<?> addIncidents(@RequestBody IncidentRequest incidentRequest){
        return technicalSpecialistService.addIncidents(incidentRequest.getMonster(),incidentRequest.getNameUniversal(),incidentRequest.getLatitude(),incidentRequest.getLongitude());
    }


    @PostMapping("/incidents")
    public ResponseEntity<?> resultFinishDone(@RequestBody OrderRequest orderRequest){
        return technicalSpecialistService.resultFinishDone(orderRequest.getId(),orderRequest.getName());
    }

}
