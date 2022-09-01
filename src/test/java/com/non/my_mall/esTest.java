package com.non.my_mall;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import com.non.my_mall.mbg.model.UmsAdmin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class esTest {
    @Autowired
    private ElasticsearchClient client;
    @Test
    public void creatInt() throws IOException {
        CreateIndexResponse indexResponse = client.indices().create(c -> c.index("user2"));
        System.out.println(indexResponse);
    }

     @Test
     public void addDocumentTest() throws IOException {
         UmsAdmin umsAdmin = new UmsAdmin();
         umsAdmin.setId(1L);
         IndexResponse user = client.index(i -> i.index("user").id("1").document(umsAdmin));
         System.out.println("user"+user);
    }


}
