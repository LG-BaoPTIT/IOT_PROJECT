//package com.example.spring.controller;
//
//import com.example.spring.payload.request.DoorStatus;
//import com.example.spring.service.DoorService;
//import com.example.spring.service.LightService;
//import com.example.spring.service.MQTTService;
//import com.example.spring.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.PublicKey;
//
//@RestController
//public class DoorController {
//    @Autowired
//    private DoorService doorService;
//
//    @Autowired
//    private MQTTService mqttService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @Autowired
//    private UserService userService;
//
////    @PostMapping("/changeDoorStatus")
////    public ResponseEntity<?> changeDoorStatus(@RequestBody DoorStatus doorStatus){
////        if(doorStatus==null){
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
////        }
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        String userName = authentication.getName();
////
////        System.out.println("Người dùng " + userName + " gửi yêu cầu đến URI /changeDoorStatus" );
////        String topic =userService.getDeviceIdByUserName(userName) + "/" + doorStatus.getDoorId() + "/" + "door_state";
////        mqttService.sendMessage(topic,doorStatus.getStatus());
////        return ResponseEntity.status(HttpStatus.OK).body("open/close door successfully.");
////
////    }
//}
