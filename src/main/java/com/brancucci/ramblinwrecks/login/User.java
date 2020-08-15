package com.brancucci.ramblinwrecks.login;

import lombok.Builder;
import lombok.Value;
import org.reactivestreams.Publisher;

@Value
@Builder
public class User {
    UserKey userKey;
}
