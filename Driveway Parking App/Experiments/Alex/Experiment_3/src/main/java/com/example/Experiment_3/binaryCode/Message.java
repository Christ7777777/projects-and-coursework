package com.example.Experiment_3.binaryCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    private String sender;
    private String message;
}
