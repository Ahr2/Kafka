package com.example.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate; 

@SpringBootApplication
public class kafkaProducerndConsumer  implements ApplicationRunner{
	
	@Autowired
   private KafkaTemplate<String, String> kafkaTemplate;

   public void sendMessage(String msg) 
   {
      kafkaTemplate.send("payments", msg);
   }

   @KafkaListener(topics = "payments", groupId = "hr-group-1")
   public void listen(String message) {
      System.out.println("Received Messasge in group - hr group-1: " + message);
   }

   public void run(ApplicationArguments args) throws Exception {
      sendMessage("Hi Welcome to Spring For Apache Kafka");
   }

	public static void main(String[] args) {
		SpringApplication.run(kafkaProducerndConsumer.class, args);
	}

}
