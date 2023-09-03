package org.example.nacosspringcloudcommonentity;

import lombok.Data;

import java.util.Date;

@Data
public class Tokens {

    private int id;
    private String accessToken;
    private Date expiration;
}
