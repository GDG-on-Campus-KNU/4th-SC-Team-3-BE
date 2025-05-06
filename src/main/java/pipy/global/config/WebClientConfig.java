package pipy.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${pipy.ai.url}")
    private String aiUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
            .baseUrl(aiUrl)
            .defaultHeader("Content-Type", "application/json")
            .build();
    }
}