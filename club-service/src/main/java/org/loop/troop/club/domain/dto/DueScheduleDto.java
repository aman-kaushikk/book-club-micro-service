package org.loop.troop.club.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DueScheduleDto {

	private LocalDate dueDate;

	private LocalTime dueTime;

	private String timeZone;

}