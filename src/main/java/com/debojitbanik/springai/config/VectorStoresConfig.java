package com.debojitbanik.springai.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorStoresConfig {
    @Bean(name = "localStoreOpenAiEmbedding")
    public VectorStore localStoreOpenAiEmbedding(EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }

    @Bean(name = "localStoreOllamaEmbedding")
    public VectorStore localStoreOllamaEmbedding(@Qualifier("ollamaEmbeddingModel") EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }
}
