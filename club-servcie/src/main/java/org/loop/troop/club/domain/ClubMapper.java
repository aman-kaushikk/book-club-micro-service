package org.loop.troop.club.domain;

import org.loop.troop.club.domain.dto.ClubDto;
import org.loop.troop.club.domain.dto.CreateClubRequest;
import org.loop.troop.club.domain.dto.MeetingDto;
import org.loop.troop.club.domain.dto.PollDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
		uses = {MemberMapper.class, MeetingMapper.class},
		injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClubMapper {

	@Mappings(value = { @Mapping(target = "clubId", ignore = true),
	@Mapping(target = "currentReadingBook", ignore = true), @Mapping(target = "readBooks", ignore = true),
	@Mapping(target = "contactLinkInfo", ignore = true), })
	ClubDto createClubTo(CreateClubRequest createClubRequest);

	ClubDto mapToDto(Club club);

	@Mapping(target = "members", ignore = true)
	@Mapping(target = "meetings", ignore = true)
	@Mapping(target = "polls", ignore = true)
	@Mapping(target = "readBooks", ignore = true)
	@Mapping(target = "futureReadBooks", ignore = true)
	@Mapping(target = "contactLinkInfo", ignore = true)
	Club mapToEntity(ClubDto clubDto);

	List<ClubDto> mapToDto(List<Club> clubs);

	List<Club> mapToEntity(List<ClubDto> clubDtoList);

	@ObjectFactory
	default Poll createPoll(PollDto dto) {
		return switch (dto.getPollType()) {
			case "YES_NO" -> new YesNoPoll();
			case "MULTIPLE_CHOICE" -> new MultipleChoicePoll();
			default -> throw new IllegalArgumentException("Unsupported poll type: " + dto.getPollType());
		};
	}

	default List<UUID> mapMembersToIds(List<Member> members) {
		return members.stream()
				.map(Member::getId)
				.collect(Collectors.toList());
	}

	default List<UUID> mapMeetingsToIds(List<Meeting> meetings) {
		return meetings.stream()
				.map(Meeting::getId)
				.collect(Collectors.toList());
	}

}