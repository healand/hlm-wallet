package com.last.lastcoin.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class HistoryEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long transferId;
  private Long memberId;
  private Long walletId;
  @Column(columnDefinition = "TEXT")
  private String counterAddress;
  @Column(columnDefinition = "TEXT")
  private String txId;
  private String type;
  private Integer status;
  private BigDecimal gasFee;
  private BigDecimal transferFee;
  private BigDecimal amount;
  private String description;
  private BigDecimal originalBalance;
  private BigDecimal afterBalance;

  @Transient
  MemberEntity member;
}
