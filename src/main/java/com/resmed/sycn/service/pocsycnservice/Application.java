package com.resmed.sycn.service.pocsycnservice;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

@SpringBootApplication
public class Application {

    protected Application() {}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    public class ElasticsearchConfiguration {

        @Bean
        public Client client(@Value("${elasticsearch.host}") String esHost, @Value("${elasticsearch.port:9300}") int esPort,
            @Value("${elasticsearch.sniff:false}") boolean esSniff)
            throws UnknownHostException {
            TransportClient client = new PreBuiltTransportClient(Settings.builder().put("client.transport.sniff", esSniff).build());
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
