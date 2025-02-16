package com.ute.auction.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class BlackList {
    private Set<String> blackListTokenSet = new HashSet<>();

    public void addToBlackListToken(String token) {
        blackListTokenSet.add(token);
    }

    public boolean isBlackListed(String token) {
        return blackListTokenSet.contains(token);
    }
}
