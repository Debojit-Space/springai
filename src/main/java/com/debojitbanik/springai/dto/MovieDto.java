package com.debojitbanik.springai.dto;

import java.util.List;
public class MovieDto {
    private String name;
    private String genre;
    private String language;
    private List<String> actors;
    private String plot;

    // Optionally, add an id if needed by your API
    private Long id;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public List<String> getActors() { return actors; }
    public void setActors(List<String> actors) { this.actors = actors; }

    public String getPlot() { return plot; }
    public void setPlot(String plot) { this.plot = plot; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
