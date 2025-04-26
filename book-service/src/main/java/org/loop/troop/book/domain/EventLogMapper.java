package org.loop.troop.book.domain;

import org.loop.troop.book.domain.modal.EventLogDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
interface EventLogMapper {

	EventLogDto getEventDto(EventLog eventLog);

	EventLog getEvent(EventLogDto eventLogDto);

	List<EventLogDto> getEventDtoList(List<EventLog> eventLog);

	List<EventLog> getEventList(List<EventLogDto> eventLogDto);

}
