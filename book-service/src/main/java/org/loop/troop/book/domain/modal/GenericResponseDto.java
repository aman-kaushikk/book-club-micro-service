package org.loop.troop.book.domain.modal;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.Instant;

@Data
public class GenericResponseDto {

	private Instant timestamp;

	private int status;

	private String message;

	private String path;

	private Integer rowCount;

	private Object data;

	public GenericResponseDto() {
	}

	public GenericResponseDto(HttpStatus status, String message, String path) {
		this.timestamp = Instant.now();
		this.status = status.value();
		this.message = message;
		this.path = path;
	}

}