package com.debojitbanik.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pdfMovies")
public class MoviesFromPdfController {

    private final VectorStore pdfVectorStore;
    @Value("classpath:/pdf/movies50.pdf")
    private Resource pdfResource;
    private final ChatClient chatClient;

    public MoviesFromPdfController(ChatClient.Builder chatClientBuilder, VectorStore pdfVectorStore) {
        this.pdfVectorStore = pdfVectorStore;
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping("/createPdfEmbeddings")
    public ResponseEntity createPdfEmbeddings(){
        TikaDocumentReader tkreader = new TikaDocumentReader(pdfResource);
        TextSplitter textSplitter = new TokenTextSplitter();
        List<Document> documents = textSplitter.split(tkreader.read());
        pdfVectorStore.add(documents);
        return ResponseEntity.ok("pdf embeddings created");
    }

    @GetMapping("/getRelevantMoviesDoc")
    public List<Document> getMovies(@RequestParam String theme){
        return pdfVectorStore.similaritySearch(SearchRequest.builder().query(theme)
                .topK(5)
                .build());
    }
    @GetMapping("/getMovie")
    public String getMovie(@RequestParam String theme){

        String systemPromptString = "give only the movie name based only from the context information that is provided to you";
        return this.chatClient.prompt()
                .system(systemPromptString)
                .advisors(new QuestionAnswerAdvisor(pdfVectorStore))
                .user(theme)
                .call().content();
    }

}
