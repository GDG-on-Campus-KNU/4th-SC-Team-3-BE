package pipy.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        final Info info = new Info()
            .title("캐릭톡 API");

        final List<Server> servers = List.of(
            new Server()
                .description("배포 서버")
                .url("https://api.pipy.me"),
            new Server()
                .description("로컬 서버")
                .url("http://localhost:8080")
        );

        return new OpenAPI()
            .info(info)
            .servers(servers);
    }
}

