package com.last.lastcoin.service.dao;

import com.last.lastcoin.domain.AuthCodeEntity;
import com.last.lastcoin.repository.AuthCodeRepository;
import com.last.lastcoin.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDaoService extends BaseService {

    private final AuthCodeRepository authCodeRepository;

    public AuthCodeEntity save(AuthCodeEntity authCodeEntity) {
        return authCodeRepository.save(authCodeEntity);
    }

    public List<AuthCodeEntity> findByPhoneAndStatus(Integer status, String phone) {
        return authCodeRepository.findAllByStatusAndPhone(status, phone);
    }
}
