package org.loop.troop.club.domain.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class PollDto {

	private Long id;

	private String title;

	private String note;

	private DueScheduleDto dueSchedule;

	private boolean anonymous;

	private List<DueScheduleDto> meetingSchedule = new ArrayList<>();

	private UUID clubId;

	private String pollType;

	private List<UUID> bookIds = new ArrayList<>();

}
