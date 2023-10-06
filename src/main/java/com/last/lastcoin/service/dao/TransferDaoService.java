package com.last.lastcoin.service.dao;

import com.last.lastcoin.domain.TransferEntity;
import com.last.lastcoin.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferDaoService {
    private final TransferRepository transferRepository;

    public TransferEntity save(TransferEntity transferEntity) {
        return transferRepository.save(transferEntity);
    }
}
