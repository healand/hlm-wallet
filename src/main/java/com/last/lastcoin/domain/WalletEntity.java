package com.last.lastcoin.domain;

import java.math.BigDecimal;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "wallet")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class WalletEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer type;
    private Long memberId;
    private String name;
    private String symbol;
    @Column(columnDefinition = "TEXT")
    private String address;
    private BigDecimal balance;

//    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "memberId", updatable = false, insertable = false)
    MemberEntity member;
}
