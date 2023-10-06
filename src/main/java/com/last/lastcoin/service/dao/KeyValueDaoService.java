package com.last.lastcoin.service.dao;

import com.last.lastcoin.domain.KeyValueEntity;
import com.last.lastcoin.enums.KeyValue;
import com.last.lastcoin.exception.CustomResponseStatusException;
import com.last.lastcoin.exception.ExceptionCode;
import com.last.lastcoin.repository.KeyValueRepository;
import com.last.lastcoin.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeyValueDaoService extends BaseService {

    private final KeyValueRepository keyValueRepository;

    public KeyValueEntity getKeyValue(String key) {
        KeyValueEntity keyValueEntity = keyValueRepository.findKeyValueEntityByKey(key)
                .orElseThrow(() -> {
                    throw new CustomResponseStatusException(ExceptionCode.KEY_NOT_FOUND, "");
                });

        return keyValueEntity;
    }

    public KeyValueEntity getKeyValue(KeyValue key) {
        KeyValueEntity keyValueEntity = keyValueRepository.findKeyValueEntityByKey(key.getKey())
                .orElseThrow(() -> {
                    throw new CustomResponseStatusException(ExceptionCode.KEY_NOT_FOUND, "");
                });

        return keyValueEntity;
    }

    @Transactional
    public Integer updateKeyValue(String key, String value) {
        if(key == null)
            throw new CustomResponseStatusException(ExceptionCode.KEY_INVALID, "");
        return keyValueRepository.update(key, value);
    }

    @Transactional
    public void createKeyValue(String key, String value) {
        KeyValueEntity entity = KeyValueEntity.builder().key(key).value(value).description(key).build();
        keyValueRepository.save(entity);
    }

    public List<KeyValueEntity> getKeyValueList(List<String> keys) {
        return keyValueRepository.findKeyValueList(keys);
    }
}
