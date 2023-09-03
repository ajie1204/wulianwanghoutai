package org.example.nacosspringcloudauth.service;


import jdk.nashorn.internal.parser.Token;

public interface TokensService {

    void saveToken(Token token);
    Token getToken(int id);
    void deleteToken(int id);
}
