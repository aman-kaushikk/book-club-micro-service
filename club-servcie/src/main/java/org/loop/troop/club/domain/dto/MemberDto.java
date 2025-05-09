package org.loop.troop.club.domain.dto;

import lombok.Data;
import java.util.UUID;


import lombok.Data;
import java.util.UUID;

@Data
public class MemberDto {
    private UUID id;
    private String name;
    private String email;
    private UUID clubId;
}