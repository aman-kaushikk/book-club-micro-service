package org.loop.troop.club.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClubDto {

    @NotNull(message = "{club.id.notnull}")
    private Long id;

    @NotBlank(message = "{club.name.blank}")
    @Size(min = 3, max = 100, message = "{club.name.size}")
    private String name;

    @NotNull(message = "{club.memberIds.notnull}")
    private List<Long> memberIds;

    @Valid
    private List<MeetingDto> meetings = new ArrayList<>();

    @Valid
    private List<PollDto> polls = new ArrayList<>();
}
