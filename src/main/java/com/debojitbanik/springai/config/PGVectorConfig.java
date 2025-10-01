package com.debojitbanik.springai.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class PGVectorConfig {
    @Value("${spring.ai.vectorstore.pgvector.index-type}")
    private String indexType;

    @Value("${spring.ai.vectorstore.pgvector.distance-type}")
    private String distanceType;

    @Value("${spring.ai.vectorstore.pgvector.dimensions}")
    private String dimensions;

    @Bean(name = "pgvectorstore")
    public VectorStore vectorStore(JdbcTemplate jdbcTemplate, @Qualifier("ollamaEmbeddingModel") EmbeddingModel embeddingModel) {
        return PgVectorStore.builder(jdbcTemplate, embeddingModel)
                .dimensions(Integer.parseInt(dimensions))
                .distanceType(PgVectorStore.PgDistanceType.valueOf(distanceType))
                .indexType(PgVectorStore.PgIndexType.valueOf(indexType))
                .initializeSchema(false)
                .schemaName("public")
                .idType(PgVectorStore.PgIdType.INTEGER)
                .vectorTableName("moviesvectorstore")
                .build();
    }

    @Bean(name = "pdfVectorStore")
    public VectorStore pdfVectorStore(JdbcTemplate jdbcTemplate, @Qualifier("ollamaEmbeddingModel") EmbeddingModel embeddingModel) {
        return PgVectorStore.builder(jdbcTemplate, embeddingModel)
                .dimensions(Integer.parseInt(dimensions))
                .distanceType(PgVectorStore.PgDistanceType.valueOf(distanceType))
                .indexType(PgVectorStore.PgIndexType.valueOf(indexType))
                .initializeSchema(true)
                .schemaName("public")
                .vectorTableName("moviesvs")
                .build();
    }
}
