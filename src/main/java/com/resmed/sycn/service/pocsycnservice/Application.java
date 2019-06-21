package com.resmed.sycn.service.pocsycnservice;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class Application {

    protected Application() {}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    public class ElasticsearchConfiguration {

        @Bean
        public Client client(@Value("${elasticsearch.host:localhost}") String esHost, @Value("${elasticsearch.port:9300}") int esPort,
            @Value("${elasticsearch.sniff:false}") boolean esSniff, @Value("${elasticsearch.node:elasticsearch}") String esNode,
            @Value("{elasticsearch.credential:}") String credential)
            throws UnknownHostException {
            TransportClient client =
                new PreBuiltTransportClient(Settings.builder().put("client.transport.sniff", esSniff).put("cluster.name", esNode)
                    .put("client.transport.ignore_cluster_name", true).put("client.transport.ping_timeout", "60s")
                    .put("discovery.type", "single-node").put("request.headers.X-Found-Cluster", esNode).build());
            TransportAddress address = new TransportAddress(InetAddress.getByName(esHost), esPort);
            client.addTransportAddress(address);
            return client;
        }

    }

    @Configuration
    @EnableWebSecurity
    public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        }
    }
}
