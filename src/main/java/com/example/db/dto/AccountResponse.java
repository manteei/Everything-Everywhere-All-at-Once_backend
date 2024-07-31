package com.example.db.dto;

import com.example.db.model.Account;
import com.example.db.model.Hero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountResponse {
    private String nick;
    private Integer countFriends;
    private Integer skill;
}
