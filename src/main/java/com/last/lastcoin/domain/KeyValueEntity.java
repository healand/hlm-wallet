package com.last.lastcoin.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "keyvalue")
public class KeyValueEntity {

    @Id
    private String key;
    private String value;
    private String description;

    public String value() {
        return value;
    }
}
