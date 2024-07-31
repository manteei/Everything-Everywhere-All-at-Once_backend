package com.example.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IncidentRequest {
    private String monster;
    private String nameUniversal;
    private float longitude;
    private float latitude;
}
