package org.loop.troop.book.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class CustomHttpClientException extends RuntimeException {

	private String responseBody;

	private HttpStatusCode status;

	public CustomHttpClientException(String responseBody, HttpStatusCode status) {
		super("Error calling remote service: " + status);
		this.responseBody = responseBody;
		this.status = status;
	}

}
