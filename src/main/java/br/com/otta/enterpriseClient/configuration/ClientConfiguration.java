package br.com.otta.enterpriseClient.configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
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
    public Client clientBuilder(ClientConfig clientConfig) {
        return ClientBuilder.newClient(clientConfig);
    }

    @Bean
    public ClientConfig clientConfig(PoolingHttpClientConnectionManager connectionManager,
            ApacheConnectorProvider apacheConnectorProvider) {
        ClientConfig clientConfig = new ClientConfig();
        // 2000 milisegundos para o timeout de leitura.
        clientConfig.property(ClientProperties.READ_TIMEOUT, 2000);
        // 500 milisegundos para o timeout de conexão.
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 500);

        clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, connectionManager);
        clientConfig.connectorProvider(apacheConnectorProvider);

        return clientConfig;
    }

    @Bean
    public PoolingHttpClientConnectionManager connectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(20);

        return connectionManager;
    }

    @Bean
    public ApacheConnectorProvider apacheConnectorProvider() {
        return new ApacheConnectorProvider();
    }
}
