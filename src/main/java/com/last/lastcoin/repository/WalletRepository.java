package com.last.lastcoin.repository;

import com.last.lastcoin.domain.WalletEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
    Optional<WalletEntity> findByMemberIdAndTypeAndSymbol(Long memberId, Integer type, String symbol);

    Optional<List<WalletEntity>> findByMemberId(Long memberId);

    Optional<WalletEntity> findByAddress(String walletAddress);
    Optional<WalletEntity> findByAddressAndType(String address, Integer type);

    @Query("select w from WalletEntity w where w.memberId =(select m.id from MemberEntity m where m.type=0) and w.type = 0")
    Optional<WalletEntity> findMasterWallet();

    @Query("select sum(w.balance) from WalletEntity w where w.type = 0")
    Optional<BigDecimal> getTotalBalance();
}
