package org.sebi;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkus.security.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RegisterAiService
public interface MyAIService {

     @SystemMessage("You are helpful bot that can chat with users")
     @UserMessage("""
       You will reply to the prompt delimited by %%%
       
       %%%
       {request.message}
       %%%
        """)
     public ChatReqResp chat(@MemoryId long memoryId, ChatReqResp request);
}
