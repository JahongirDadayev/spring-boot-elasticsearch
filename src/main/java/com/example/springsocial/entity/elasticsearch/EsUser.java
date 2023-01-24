package com.example.springsocial.entity.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "es_user")
@Builder
public class EsUser {
    @Id
    @Field(name = "id", type = FieldType.Text)
    private String id;

    @Field(name = "name", type = FieldType.Text)
    private String name;
}
