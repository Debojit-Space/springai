package com.debojitbanik.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieRecommendController {

    private final ChatClient chatClient;

    public MovieRecommendController(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory) {
        this.chatClient = chatClientBuilder
                .defaultAdvisors(PromptChatMemoryAdvisor.builder(chatMemory).build(),
                        SimpleLoggerAdvisor.builder().build())
                .build();
    }
    @GetMapping("/recommend")
    public String getRecommendations(@RequestParam String language, @RequestParam String genre){

        String promptTemplateString = """
                Give me a list of top 5 {language} movies of genre {genre}
                give me in the below format
                Movie: [title]
                Plot: [movie plot in less 50 words]
                Director: [director]
                """;

        PromptTemplate promptTemplate = PromptTemplate.builder()
                .template(promptTemplateString)
                .variables(Map.of("language", language, "genre", genre))
                .build();

        return this.chatClient.prompt(promptTemplate.create())
                .system("You are a movie expert")
                .call()
                .content();

    }
}
