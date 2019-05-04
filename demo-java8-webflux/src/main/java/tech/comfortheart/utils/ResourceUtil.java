package tech.comfortheart.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Base64;

@Service
public class ResourceUtil {

    @Value("${spring.cloud.config.uri}")
    String configServerHost;
    @Value("${spring.cloud.config.username}")
    String configUsername;
    @Value("${spring.cloud.config.password}")
    String configPassword;
    @Value("${spring.cloud.config.label}")
    String label;
    @Value("${spring.config.name}")
    String configName;

    public String getResourceFromConfigServer(String plainTextFile) {
        WebClient webClient = WebClient.create(configServerHost);

        StringBuilder uriSB = new StringBuilder('/')
                .append(configName).append('/').append("default").append('/')
                .append(label).append('/')
                .append(plainTextFile);

        return webClient.get()
                .uri(uriSB.toString())
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((configUsername + ":" + configPassword).getBytes()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
