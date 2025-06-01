package com.br.api.favorites.infrastructure.mongo.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "favorites")
public class FavoritesDocument {

    @Id
    private String id;

    @Field("clientId")
    private String clientId;

    @Field("skus")
    private List<String> skus = new ArrayList<>();

}
