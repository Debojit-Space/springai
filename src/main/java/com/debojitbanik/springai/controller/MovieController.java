package com.debojitbanik.springai.controller;

import com.debojitbanik.springai.entity.Movie;
import com.debojitbanik.springai.repository.MovieRepository;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private final MovieRepository movieRepository;
    private final VectorStore vectorStore;

    public MovieController(MovieRepository movieRepository, @Qualifier("pgvectorstore") VectorStore vectorStore) {
        this.movieRepository = movieRepository;
        this.vectorStore = vectorStore;
    }

    @PostMapping("/createEmbeddings")
    public ResponseEntity createEmbeddings(){
        List<Movie> movies = movieRepository.findAll();
        List<Document> documents = new ArrayList<>();
        for(Movie movie: movies){
            Map<String, Object> metadata = Map.of("name",movie.getName(),
                    "genre", movie.getGenre(),
                    "language", movie.getLanguage(),
                    "actors", String.join(", ", movie.getActors()));
            Document doc = new Document(movie.getId().toString(), movie.getPlot(), metadata);
            documents.add(doc);
        }
        vectorStore.add(documents);
        return ResponseEntity.ok("embeddings created");
    }

    @PostMapping("/search")
    public List<Movie> searchSimilar(@RequestBody String query) {
        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder().query(query)
                .topK(3)
                .build());
        List<Optional<Movie>> movies = documents.stream().map(document -> movieRepository.findById(Long.parseLong(document.getId())))
                .collect(Collectors.toList());
        return movies.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }
}

