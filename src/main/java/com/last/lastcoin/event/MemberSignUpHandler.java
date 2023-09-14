package com.last.lastcoin.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component

@RequiredArgsConstructor
public class MemberSignUpHandler {

    @TransactionalEventListener
    public void memberSignUpEventListener(MemberSignedUpEvent event) {
    }
}
