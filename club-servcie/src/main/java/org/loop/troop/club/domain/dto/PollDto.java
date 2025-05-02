package org.loop.troop.club.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PollDto {

    private String question;
    private List<String> options;

    // Getters and Setters
}
