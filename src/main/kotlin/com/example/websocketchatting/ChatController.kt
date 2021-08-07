package com.example.websocketchatting

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Controller


@Controller
class ChatController {
    @Autowired
    lateinit var messagingTemplate: SimpMessageSendingOperations

    @MessageMapping("/chat/message")
    fun message(message: ChatMessage) {
        if (MessageType.JOIN == message.type) message.message = message.sender + "님이 입장하셨습니다."
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.roomId, message)
    }
}
