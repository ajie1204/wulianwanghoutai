//package org.example.nacosspringcloudauth.service.serviceImpl;
//
//import jdk.nashorn.internal.parser.Token;
//import org.example.nacosspringcloudauth.service.TokensService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TokensServiceImpl implements TokensService {
//
//    @Autowired
//    private TokenRepository tokenRepository;
//
//    @Override
//    public void saveToken(Token token) {
//        tokenRepository.save(token);
//    }
//
//    @Override
//    public Token getToken(int id) {
//        return tokenRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public void deleteToken(int id) {
//        tokenRepository.deleteById(id);
//    }
//}