package com.last.lastcoin.domain;

import com.last.lastcoin.enums.HistoryType;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transfer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class TransferEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(columnDefinition = "TEXT")
  private String txId;
  private String type;
  private Integer status;
  private BigDecimal amount;
  private BigDecimal gasFee;
  private BigDecimal transferFee;
  private String description;

  @Column(columnDefinition = "TEXT")
  private String fromWalletAddress;
  private Long fromMemberId;
  private Long fromWalletId;
  private BigDecimal fromAfterBalance;

  private Long toMemberId;
  private Long toWalletId;
  private BigDecimal toAfterBalance;
  @Column(columnDefinition = "TEXT")
  private String toWalletAddress;
  @Column(columnDefinition = "TEXT")
  private String metadata;

  public HistoryEntity getBaseHistoryEntity(HistoryType historyType) {
    HistoryEntity historyentity = HistoryEntity.builder()
            .transferId(id)
            .amount(amount)
            .type(historyType.getValue())
            .description(description)
            .status(status)
            .txId(txId)
            .build();
    switch(historyType) {
      case HISTORY_WITHDRAW_INSIDE:
      case HISTORY_WITHDRAW_OUTSIDE:
      case HISTORY_WITHDRAW_ETC:
      case HISTORY_WITHDRAW_LOCK_UP_REAL:
        historyentity.setMemberId(fromMemberId);
        historyentity.setWalletId(fromWalletId);
        historyentity.setCounterAddress(toWalletAddress);
        historyentity.setGasFee(gasFee);
        historyentity.setTransferFee(transferFee);
        break;

      case HISTORY_DEPOSIT_INSIDE:
      case HISTORY_DEPOSIT_OUTSIDE:
      case HISTORY_DEPOSIT_LOCK_UP:
      case HISTORY_DEPOSIT_ETC:
        historyentity.setMemberId(toMemberId);
        historyentity.setWalletId(toWalletId);
        historyentity.setCounterAddress(fromWalletAddress);
        historyentity.setGasFee(BigDecimal.ZERO);
        historyentity.setTransferFee(BigDecimal.ZERO);
        break;

      case HISTORY_WITHDRAW_LOCK_UP_DATA:
        historyentity.setMemberId(toMemberId);
        historyentity.setCounterAddress(toWalletAddress);
        historyentity.setGasFee(BigDecimal.ZERO);
        historyentity.setTransferFee(BigDecimal.ZERO);
    }

    return historyentity;
  }

  public HistoryEntity getFeeHistoryEntity(WalletEntity companyWallet) {
    return HistoryEntity.builder()
            .transferId(id)
            .amount(transferFee)
            .type(HistoryType.HISTORY_DEPOSIT_TRANSACTION_FEE.getValue())
            .description("거래 수수료")
            .status(1)
            .walletId(companyWallet.getId())
            .memberId(companyWallet.getMemberId())
            .counterAddress(fromWalletAddress)
            .gasFee(BigDecimal.ZERO)
            .transferFee(BigDecimal.ZERO)
            .originalBalance(companyWallet.getBalance())
            .afterBalance(companyWallet.getBalance().add(transferFee))
            .txId(txId)
            .build();
  }
}
