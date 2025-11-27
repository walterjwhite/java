package com.walterjwhite.examples.spring.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String from;
    private String content;
    private long timestamp;
    private String type;
}
