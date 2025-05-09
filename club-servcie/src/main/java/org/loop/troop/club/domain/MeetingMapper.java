package org.loop.troop.club.domain;

import org.loop.troop.club.domain.Club;
import org.loop.troop.club.domain.Meeting;
import org.loop.troop.club.domain.Member;
import org.loop.troop.club.domain.dto.CreateMeetingDTO;
import org.loop.troop.club.domain.dto.MeetingDTO;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MeetingMapper {

	@Mapping(target = "club", ignore = true)
	@Mapping(target = "host", ignore = true)
	@Mapping(target = "attendees", ignore = true)
	@Mapping(target = "id", ignore = true)
	Meeting toEntity(CreateMeetingDTO dto);

	@Mapping(target = "clubId", source = "club.clubId")
	@Mapping(target = "hostId", source = "host.id")
	@Mapping(target = "hostName", source = "host.name")
	@Mapping(target = "attendeeIds", source = "attendees")
	MeetingDTO toDto(Meeting meeting);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "club", ignore = true)
	@Mapping(target = "host", ignore = true)
	@Mapping(target = "attendees", ignore = true)
	@Mapping(target = "id", ignore = true)
	void updateMeetingFromDto(MeetingDTO dto, @MappingTarget Meeting entity);

	default List<UUID> mapAttendees(List<Member> attendees) {
		return attendees.stream().map(Member::getId).collect(Collectors.toList());
	}

	default UUID mapClub(Club club) {
		return club != null ? club.getClubId() : null;
	}

	default UUID mapHost(Member host) {
		return host != null ? host.getId() : null;
	}

}