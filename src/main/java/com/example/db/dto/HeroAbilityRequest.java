package com.example.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HeroAbilityRequest {
    private String title;
    private Integer mastery_percentage;
}
