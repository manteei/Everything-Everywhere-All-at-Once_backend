package com.example.db.dto;

import java.sql.Timestamp;

public interface MessageProjection {
    String getName();
    String getContent();
    Timestamp getTime1();
}

