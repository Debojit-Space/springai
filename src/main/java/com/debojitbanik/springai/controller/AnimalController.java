package com.debojitbanik.springai.controller;

import com.debojitbanik.springai.model.Animal;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final ChatClient chatClient;
    public AnimalController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    List<Animal> findAll() {
        PromptTemplate pt = new PromptTemplate("""
                Generate a list of 5 popular animals.
                Each object should contain an auto-incremented id field.
                """);
        return this.chatClient.prompt(pt.create())
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }
}
