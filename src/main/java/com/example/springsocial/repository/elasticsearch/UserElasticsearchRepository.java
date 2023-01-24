package com.example.springsocial.repository.elasticsearch;

import com.example.springsocial.entity.elasticsearch.EsUser;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserElasticsearchRepository extends ElasticsearchRepository<EsUser, String> {

    @Query(query = "{\"fuzzy\": {\"name\": {\"value\": \"?0\",\"fuzziness\":\"auto\"}}}")
    List<EsUser> findByNameFuzzy(String name);
}
