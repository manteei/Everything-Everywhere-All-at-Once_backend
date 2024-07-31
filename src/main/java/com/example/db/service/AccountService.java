package com.example.db.service;

import com.example.db.dto.AccountResponse;
import com.example.db.dto.HeroAbilityRequest;
import com.example.db.dto.MessageProjection;
import com.example.db.model.*;
import com.example.db.model.enums.RoleName;
import com.example.db.model.enums.StatusFriends;
import com.example.db.model.enums.StatusOrder;
import com.example.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final HeroRepository heroRepository;
    private final RoleRepository roleRepository;
    private final FriendsRepository friendsRepository;
    private final MessageRepository messageRepository;
    private final QuestRepository questRepository;
    private final OrderRepository orderRepository;
    private final EmployerRepository employerRepository;
    private final HeroAbilityRepository  heroAbilityRepository;
    private final AbilityRepository abilityRepository;
    private final UniversalRepository universalRepository;

    public ResponseEntity<?> getProfileInformation(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = roleRepository.findByUser(userRepository.findByLogin(username).getLogin());
        if (Objects.equals(role, RoleName.Герой.toString())) {

            return new ResponseEntity<>(new AccountResponse(accountRepository.findByLogin(username).getNickname(),accountRepository.findByLogin(username).getFriends(),heroRepository.findByLogin(username).getSkill()), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new AccountResponse(accountRepository.findByLogin(username).getNickname(),accountRepository.findByLogin(username).getFriends(), null), HttpStatus.OK);

    }
    public ResponseEntity<?> changeNickname(String newNick){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        accountRepository.findByLogin(username).setNickname(newNick);
        accountRepository.save(accountRepository.findByLogin(username));
        return new ResponseEntity<>("Никнейм успешно изменен!",HttpStatus.OK);
    }


    public ResponseEntity<?> getFriends(){
        return new ResponseEntity<>(friendsRepository.getFriends(SecurityContextHolder.getContext().getAuthentication().getName(),StatusFriends.REQUEST_ACCEPTED.toString()),HttpStatus.OK);
    }

    public ResponseEntity<?> deleteFriends(String name){
        friendsRepository.deleteFriendsByName(SecurityContextHolder.getContext().getAuthentication().getName(),name);
        Account user = accountRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setFriends(user.getFriends()-1);
        Account friend = accountRepository.findByLogin(name);
        friend.setFriends(friend.getFriends()-1);
        accountRepository.save(user);
        accountRepository.save((friend));
        return new ResponseEntity<>("Друг успешно удален!",HttpStatus.OK);
    }

    public ResponseEntity<?> getNewFriends(){
        return new ResponseEntity<>(friendsRepository.getPerson(SecurityContextHolder.getContext().getAuthentication().getName()),HttpStatus.OK);
    }

    public ResponseEntity<?> sendFriendshipRequest(String name){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Friends sendNewFriend = Friends.builder()
                .user1(userRepository.findByLogin(username))
                .user2(userRepository.findByLogin(name))
                .status(StatusFriends.REQUEST_SENT)
                .build();
        friendsRepository.save(sendNewFriend);
        return new ResponseEntity<>("Заявка в друзья отправлена!",HttpStatus.OK);
    }

    public ResponseEntity<?> requestFriendshipRequest(){
        return new ResponseEntity<>(friendsRepository.getFriendsRequest(SecurityContextHolder.getContext().getAuthentication().getName(),StatusFriends.REQUEST_SENT.toString()),HttpStatus.OK);
    }

    public ResponseEntity<?> acceptFriendshipRequest(String name){
        Friends friends = friendsRepository.findByUser1AndUser2(userRepository.findByLogin(name).getLogin(),userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getLogin());
        friends.setStatus(StatusFriends.REQUEST_ACCEPTED);
        friendsRepository.save(friends);

        Account user = accountRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setFriends(user.getFriends()+1);
        Account friend = accountRepository.findByLogin(name);
        friend.setFriends(friend.getFriends()+1);
        accountRepository.save(user);
        accountRepository.save((friend));
        return new ResponseEntity<>("Пользователь добавлен в друзья!",HttpStatus.OK);
    }

    public ResponseEntity<?> getMessages(String name){
        List<MessageProjection> allMessage = messageRepository.getAllMessageByUser(userRepository.findByLogin(name).getLogin(),userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getLogin());
        return new ResponseEntity<>(allMessage,HttpStatus.OK);
    }

    public ResponseEntity<?> sendMessage(String name, String text){
        Message newMessage = Message.builder()
                .sender(userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()))
                .receiver(userRepository.findByLogin(name))
                .content(text)
                .time(getCurrentTimestampWithAutomaticDayAndMinute())
                .build();

        messageRepository.save(newMessage);
        return new ResponseEntity<>("Сообщение отправлено!",HttpStatus.OK);
    }



    public Timestamp getCurrentTimestampWithAutomaticDayAndMinute() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int day = currentDateTime.getDayOfMonth();
        int minute = currentDateTime.getMinute();
        int hour = currentDateTime.getHour();
        int month = currentDateTime.getMonthValue();
        int year = currentDateTime.getYear();

        LocalDateTime customDateTime = LocalDateTime.of(year, month, day, hour, minute);

        return Timestamp.valueOf(customDateTime);
    }

    public ResponseEntity<?> getAbstractTasks() {
        return new ResponseEntity<>(questRepository.findRandomQuests(),HttpStatus.OK);
    }

    public ResponseEntity<?> resultMoving(String result) {
        if (Objects.equals(result, "Done")) {
            Hero hero = heroRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
            hero.setSkill(hero.getSkill()+1);
            heroRepository.save(hero);
            return new ResponseEntity<>("Скилл увеличен",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Скилл не изменился",HttpStatus.OK);
    }
    public ResponseEntity<?> getAllOrder(){
        return new ResponseEntity<>(orderRepository.getOrders(),HttpStatus.OK);
    }

    public ResponseEntity<?> reservationOrder(Integer id, String name){
        Order order = orderRepository.findById(id);
        order.setStatus(StatusOrder.PERFORMANCE);
        orderRepository.save(order);

        Employer employer = Employer.builder()
                .order(order)
                .hero(userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()))
                .coordinator(userRepository.findByLogin(name))
                .build();
        employerRepository.save(employer);

        return  new ResponseEntity<>(orderRepository.getAddress(id),HttpStatus.OK);
    }

    public ResponseEntity<?> getSkills(){
        return new ResponseEntity<>(heroAbilityRepository.getAbility(SecurityContextHolder.getContext().getAuthentication().getName()),HttpStatus.OK);
    }

    public ResponseEntity<?> saveSkills(List<HeroAbilityRequest> list){
        for (HeroAbilityRequest heroAbilityRequest : list) {
            Ability ability = abilityRepository.findByTitle(heroAbilityRequest.getTitle());

            System.out.println(ability.getId());

            HeroAbility heroAbility = heroAbilityRepository.findByAbilityAndUser(ability, userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
            heroAbility.setMasteryPercentage(heroAbilityRequest.getMastery_percentage());
            Universal universal = universalRepository.findByDistance(chooseUniversal(heroAbility.getMasteryPercentage()));
            heroAbility.setUniversal(universal);
            heroAbilityRepository.save(heroAbility);
        }

        return new ResponseEntity<>("Данные анкеты сохранены!",HttpStatus.OK);
    }

    private Integer chooseUniversal(Integer percentage){
        List<Integer> dist = universalRepository.getDistinct();
        Integer result = 0;
        System.out.println(dist);
        for (Integer integer : dist) {
            if (integer <= 100 - percentage)
                result = integer;
        }
        return result;
    }

    public ResponseEntity<?> getSkillModel(){
        return new ResponseEntity<>(heroAbilityRepository.getUserModel(SecurityContextHolder.getContext().getAuthentication().getName()),HttpStatus.OK);
    }
}
