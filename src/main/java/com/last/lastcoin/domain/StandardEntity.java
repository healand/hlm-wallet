package com.last.lastcoin.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "standard")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StandardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String phone;
    private BigDecimal amount;
}
