package com.springboot.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Message {

   private String content;
   private String link;
   private String linkContent;
   @Builder.Default
   private MessageType type=MessageType.red;
}
