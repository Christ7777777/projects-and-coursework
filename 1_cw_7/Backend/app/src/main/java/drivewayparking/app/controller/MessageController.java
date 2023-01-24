package drivewayparking.app.controller;

import drivewayparking.app.entity.Message;
import drivewayparking.app.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "MessageController", description = "REST APIs for the Message Entity")
@RestController
@RequestMapping("/messages")
public class MessageController {
//
//    @Autowired
//    private MessageService service;
//
//    @ApiOperation(value = "Get messages by sender and receiver", notes = "Get all messages by the given sender email and receiver email from the database", response = Iterable.class)
//    @GetMapping("/getMessages/Sender/Receiver/{senderId}/{receiverId}")
//    public List<Message> getMessagesBySenderReceiver(@PathVariable Long senderId, @PathVariable Long receiverId){
//        return service.getMessagesBySenderReceiver(senderId, receiverId);
//    }
//
//    @ApiOperation(value = "Create new message", notes = "Create new message and save it to the database", response = Integer.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 0, message = "OK"),
//            @ApiResponse(code = 1, message = "Not Found"),
//            @ApiResponse(code = 2, message = "Error") })
//    @PostMapping("/")
//    public Integer createMessage(@RequestBody Message message){
//        return service.saveMessage(message);
//    }
//
//    @ApiOperation(value = "Delete message", notes = "Delete message by the given id from the database", response = Integer.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 0, message = "OK"),
//            @ApiResponse(code = 1, message = "Not Found"),
//            @ApiResponse(code = 2, message = "Error") })
//    @DeleteMapping("/{id}")
//    public Integer deleteMessage(@PathVariable Long id) {
//        return service.deleteMessage(id);
//    }
}
