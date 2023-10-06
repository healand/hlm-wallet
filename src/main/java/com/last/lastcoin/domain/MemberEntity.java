package com.last.lastcoin.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer type;
  private String name;
  private String phone;
  private String fcmToken;
  private String pin;
  private Integer status;
  private Long standard; // 가입 당시 투자 기준 금액

  private LocalDateTime deletedTime;

  @JsonBackReference
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "memberId", updatable = false, insertable = false)
  List<WalletEntity> walletList;

}
