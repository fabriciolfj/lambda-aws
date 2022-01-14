package com.github.fabriciolfj.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String id;
    private String name;
    @JsonProperty
    private List<Details> details;

    public void enrichDetails() {
        var detail1 = new Details();
        detail1.setDescribe(UUID.randomUUID().toString());

        var detail2 = new Details();
        detail2.setDescribe(UUID.randomUUID().toString());

        if (details == null) {
            this.details = new ArrayList<>();
        }

        details.add(detail1);
        details.add(detail2);
    }
}
