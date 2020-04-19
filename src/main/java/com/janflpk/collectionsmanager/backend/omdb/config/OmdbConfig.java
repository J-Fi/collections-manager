package com.janflpk.collectionsmanager.backend.omdb.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OmdbConfig {

    @Value("${omdb.api.endpoint.prod}")
    private String omdbApiEndpoint;
}
