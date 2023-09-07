package com.pet.sitter.chat.controller;

import com.pet.sitter.chat.dto.ChatRoomDTO;
import com.pet.sitter.chat.service.ChatRoomService;
import com.pet.sitter.common.entity.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @GetMapping("/room")
    public String rooms(Model model) {
        List<ChatRoom> chatRoomsList = chatRoomService.getAllChatRooms();
        model.addAttribute("chatrooms", chatRoomsList);

        return "/chat/room";
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomService.getAllChatRooms();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoomDTO createRoom(@RequestParam("petSitterNo") Long id) {
        return chatRoomService.createChatRoom(id);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable Long roomId) {
        ChatRoom chatRoom = chatRoomService.getChatRoomById(roomId);

        if (chatRoom != null) {
            model.addAttribute("roomId", roomId);
            model.addAttribute("roomName", chatRoom.getPetsitter().getPetTitle());

            return "/chat/roomdetail";
        } else {
            return "redirect:/chat/room";
        }
    }
}