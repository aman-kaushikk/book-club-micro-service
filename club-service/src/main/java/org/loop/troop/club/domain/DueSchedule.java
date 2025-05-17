package org.loop.troop.club.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
@Getter
@Setter
public class DueSchedule {

	@Column(nullable = false)
	private LocalDate dueDate;

	@Column(nullable = false)
	private LocalTime dueTime;

	@Column(nullable = false)
	private String timeZone;

}
