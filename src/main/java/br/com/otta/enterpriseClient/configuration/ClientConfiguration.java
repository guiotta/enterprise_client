package br.com.otta.enterpriseClient.configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.otta.enterpriseApi.api.EnterpriseApi;

@Configuration
public class ClientConfiguration {

    @Bean
    public EnterpriseApi enterpriseApi(Client client) {
        WebTarget target = client.target("http://localhost:8080/");
        return WebResourceFactory.newResource(EnterpriseApi.class, target);
    }

    @Bean
    public Client clientBuilder() {
        return ClientBuilder.newClient();
    }
}
