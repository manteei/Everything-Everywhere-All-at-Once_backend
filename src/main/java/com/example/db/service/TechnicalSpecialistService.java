package com.example.db.service;

import com.example.db.model.*;
import com.example.db.model.enums.StatusOrder;
import com.example.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TechnicalSpecialistService {
    private final QuestRepository questRepository;
    private final UserRepository userRepository;
    private final IncidentInformationRepository incidentInformationRepository;
    private final UniversalRepository universalRepository;
    private final AddressRepository addressRepository;
    private final MonsterRepository monsterRepository;
    private final OrderRepository orderRepository;
    private final EmployerRepository employerRepository;

    public ResponseEntity<?> getTasks(){
        return new ResponseEntity<>(questRepository.findAllQuests(), HttpStatus.OK);
    }

    public ResponseEntity<?> addTask(String nameQuest){
        Quest newQuest = Quest.builder()
                .name(nameQuest)
                .user(userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()))
                .build();
        questRepository.save(newQuest);
        return new ResponseEntity<>("Задание успешно добавлено!", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteTask(String nameQuest){
        if (Objects.equals(questRepository.findByName(nameQuest).getUser().getLogin(), SecurityContextHolder.getContext().getAuthentication().getName()))
            questRepository.deleteByName(nameQuest);
        return new ResponseEntity<>("Задание успешно удалено!", HttpStatus.OK);

    }


    public ResponseEntity<?> addIncidents(String monster,String nameUniversal,float longitude,float latitude){
        Universal universal = universalRepository.findByName(nameUniversal);
        Address address  = Address.builder()
                .universal_id(universal)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        addressRepository.save(address);

        IncidentInformation incidentInformation = IncidentInformation.builder()
                .address(address)
                .monster(monsterRepository.findByName(monster))
                .user(userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()))
                .build();
        incidentInformationRepository.save(incidentInformation);

        Order order = Order.builder()
                .incidentInformation(incidentInformation)
                .status(StatusOrder.WAITING)
                .build();
        orderRepository.save(order);
        return new ResponseEntity<>("Добавлен новый инцидент!", HttpStatus.OK);
    }

    public ResponseEntity<?> getOngoingOrders(){

        return new ResponseEntity<>(employerRepository.getEmployersByOrderStatus(StatusOrder.PERFORMANCE.toString()), HttpStatus.OK);
    }

    public ResponseEntity<?> resultFinishDone(Integer id, String result){
        Order order = orderRepository.findById(id);
        if (Objects.equals(result, "Done")){
            order.setStatus(StatusOrder.DONE);
        }
        else order.setStatus(StatusOrder.WAITING);
        orderRepository.save(order);
        return new ResponseEntity<>("Данные заказа обновлены!", HttpStatus.OK);
    }

    public ResponseEntity<?> getAllMonsters(){
        return  new ResponseEntity<>(monsterRepository.allNameMonsters(), HttpStatus.OK);
    }

    public ResponseEntity<?> getAllUniversals(){
        return new ResponseEntity<>(universalRepository.allNameUniversals(), HttpStatus.OK);
    }
}
