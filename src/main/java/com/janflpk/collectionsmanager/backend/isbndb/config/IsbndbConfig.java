package com.janflpk.collectionsmanager.backend.isbndb.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class IsbndbConfig {
    @Value("${isbndb.api.endpoint.prod}")
    private String isbndbApiEndpoint;
}
