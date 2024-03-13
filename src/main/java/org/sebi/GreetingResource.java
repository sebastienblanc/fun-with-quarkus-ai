package org.sebi;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;


@Path("/chat")
public class GreetingResource {

    @Inject
    MyAIService myAIService;

    private ValueCommands<String, Long> countCommands; 
 

    public GreetingResource(RedisDataSource redisDataSource) {
        this.countCommands = redisDataSource.value(Long.class);
    }

    @POST
    public ChatReqResp newChat(ChatReqResp request) {
        long id = countCommands.incr("chat:id");
        request.setId(id);
        return myAIService.chat(id,request);
    }
}
