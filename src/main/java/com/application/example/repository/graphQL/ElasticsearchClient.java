package com.application.example.repository.graphQL;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ElasticsearchClient {

    @Bean
    public RestHighLevelClient client() {
        final List<String> hosts = List.of("localhost:9200");

        log.info("elasticsearch hosts to be configured in REST client: {}", hosts);

        final List<Node> nodes = new ArrayList<>();
        hosts.forEach(host -> {

            final String IPAddress = host.split(":")[0];
            final String port = host.split(":")[1];
            final Node node = new Node(new HttpHost(IPAddress, Integer.parseInt(port)));
            nodes.add(node);
        });

        return new RestHighLevelClient(RestClient.builder(nodes.toArray(new Node[0])));
    }
}