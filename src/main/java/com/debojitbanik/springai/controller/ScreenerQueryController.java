package com.debojitbanik.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/screener")
public class ScreenerQueryController {

    private final ChatClient chatClient;
    @Autowired
    private final VectorStore vectorStore;
    public ScreenerQueryController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.vectorStore = vectorStore;
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String question){
        String systemPromptString = """
                 "You are a strict financial domain expert. Answer only from context. If the answer is not in the provided data, say 'Not found in data.' Give concise, professional explanations. Do not hallucinate. You are supposed to convert human text into screener query. (screener.in)
                Follow this instructions strictly
                1. You need to use only the metric name provided inside quotes " ", DONT USE the aliases as well.
                2. Dont use any random name or create on your own, if u dont find any relevant metric name, dont include it in the query but never create ur own metric name.
                3. Dont use " " in any metric while forming query, like "pat" < 30 should not be written, rather pat < 30 is right
                Some examples for your reference:
                1. if I say "Companies whose mcap is greater than 2000 and pat more than 20"
                                
                your answer will be
                                
                "Market Capitalization > 2000 AND
                Profit after tax > 20"
                                
                """;
        //SearchRequest searchRequest = SearchRequest.builder().query(question).topK(5).build();
        return this.chatClient.prompt()
                .system(systemPromptString)
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .user(question)
                .call().content();

    }

    @GetMapping("/docs")
    public List<Document> getAllDocuments() {
        // Use a dummy query and high topK to fetch as many as possible
        SearchRequest request = SearchRequest.builder()
                .query("marketcap")  // Empty or generic query to match broadly
                .topK(5)  // Adjust based on your index size; max depends on Pinecone limits
                .build();

        List<Document> documents = vectorStore.similaritySearch(request);

        // Log or process the results
        documents.forEach(doc -> {
            System.out.println("ID: " + doc.getId());
            System.out.println("Content: " + doc.getFormattedContent());
            System.out.println("Metadata: " + doc.getMetadata());
        });

        return documents;
    }
}
