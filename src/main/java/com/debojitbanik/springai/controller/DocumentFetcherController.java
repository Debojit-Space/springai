package com.debojitbanik.springai.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/docs")
public class DocumentFetcherController {

    private final VectorStore vectorStore;

    public DocumentFetcherController(@Qualifier("localStoreOpenAiEmbedding") VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @GetMapping("/index")
    public String indexDocument(){
        vectorStore.add(movielist());
        return "ok";
    }

    @GetMapping("/movies")
    public List<Document> getDocument(@RequestParam String genre){
        List<Document> results = vectorStore.similaritySearch(
                SearchRequest.builder().query(genre)
                        .topK(3)
                        .build()
        );
        return results;
    }

    public List<Document> movielist(){
        return List.of(
                new Document(
                        "Two imprisoned men bond over years, finding solace and eventual redemption through acts of decency.",
                        Map.of("title","The Shawshank Redemption","year",1994,"genre","Drama")
                ),
                new Document(
                        "A thief who steals corporate secrets through dream-sharing is given a chance to redeem himself.",
                        Map.of("title","Inception","year",2010,"genre","Sci-Fi")
                ),
                new Document(
                        "Batman faces the Joker, a criminal mastermind bent on plunging Gotham into anarchy.",
                        Map.of("title","The Dark Knight","year",2008,"genre","Action")
                ),
                new Document(
                        "An organized crime dynasty's aging patriarch transfers control to his reluctant son.",
                        Map.of("title","The Godfather","year",1972,"genre","Crime")
                ),
                new Document(
                        "After a computer hacker learns about the true nature of his reality, he joins a rebellion against its controllers.",
                        Map.of("title", "The Matrix", "year", 1999, "genre", "Sci-Fi")
                ),
                new Document(
                        "While trying to return to his family, a clownfish traverses the ocean with the help of a forgetful friend.",
                        Map.of("title", "Finding Nemo", "year", 2003, "genre", "Animation")
                ),
                new Document(
                        "A young lion prince flees his kingdom only to learn the true meaning of responsibility and bravery.",
                        Map.of("title", "The Lion King", "year", 1994, "genre", "Animation")
                ),
                new Document(
                        "A washed-up superhero actor attempts to revitalize his career by writing and starring in a Broadway play.",
                        Map.of("title", "Birdman", "year", 2014, "genre", "Drama")
                ),
                new Document(
                        "A linguist is recruited to communicate with extraterrestrial visitors and discovers the true meaning of time.",
                        Map.of("title", "Arrival", "year", 2016, "genre", "Sci-Fi")
                ),
                new Document(
                        "In a post-apocalyptic wasteland, a drifter and a rebel join forces to overthrow a tyrant.",
                        Map.of("title", "Mad Max: Fury Road", "year", 2015, "genre", "Action")
                ),
                new Document(
                        "A janitor at M.I.T. has a gift for mathematics but needs help from a psychologist to find direction in his life.",
                        Map.of("title", "Good Will Hunting", "year", 1997, "genre", "Drama")
                ),
                new Document(
                        "A lawyer defends a black man accused of raping a white woman in 1930s Alabama.",
                        Map.of("title", "To Kill a Mockingbird", "year", 1962, "genre", "Drama")
                )
        );
    }
}
