package com.example.Experiment_3;

import com.example.Experiment_3.binaryCode.BinaryMessage;
import com.example.Experiment_3.binaryCode.BinaryMessageRepository;
import com.example.Experiment_3.binaryCode.Message;
import com.example.Experiment_3.binaryCode.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "{com.example.Experiment_3.*}")
public class Experiment3Application {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private BinaryMessageRepository binaryMessageRepository;

	@PostConstruct
	public void addDataToDataBase()
	{
		messageRepository.saveAll(Stream.of(new Message("Alex", "GitLab sucks"), new Message("Lindsay", "COM S 309")).collect(Collectors.toList()));
		binaryMessageRepository.saveAll(Stream.of(new BinaryMessage("A", "1010"), new BinaryMessage("B", "1010101")).collect(Collectors.toList()));
	}

	@GetMapping(path = "/getMessage")
	public List<Message> getMessage() {
		return messageRepository.findAll();
	}

	@GetMapping("/getBinaryMessage")
	public List<BinaryMessage> getBinaryMessage() {
		return binaryMessageRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(Experiment3Application.class, args);
	}

}
