package com.last.lastcoin.event;

import com.last.lastcoin.domain.MemberEntity;
import lombok.Data;

@Data
public class MemberSignedUpEvent {
    MemberEntity memberEntity;

    public MemberSignedUpEvent(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }
}
