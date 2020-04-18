package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"})
@Data
@Accessors(chain = true)
public class Document extends Base {

    @NotNull
    private String name;

    private String author;

    private int pageCount;
}
