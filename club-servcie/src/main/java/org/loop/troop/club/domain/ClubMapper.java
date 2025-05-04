package org.loop.troop.club.domain;





import org.loop.troop.club.domain.dto.ClubDto;
import org.loop.troop.club.domain.dto.PollDto;
import org.loop.troop.club.domain.dto.MeetingDTO;
import org.mapstruct.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
		uses = {MemberMapper.class, MeetingMapper.class},
		injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClubMapper {

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



//
//import org.loop.troop.club.domain.dto.ClubDto;
//import org.loop.troop.club.domain.dto.PollDto;
//import org.loop.troop.club.domain.dto.MeetingDTO;
//import org.mapstruct.*;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Mapper(componentModel = "spring",
//		uses = {MemberMapper.class, MeetingMapper.class, PollMapper.class},
//		injectionStrategy = InjectionStrategy.CONSTRUCTOR)
//public interface ClubMapper {
//	@Mapping(target = "memberIds", source = "members")
//	@Mapping(target = "meetingIds", source = "meetings")
//	@Mapping(target = "pollIds", source = "polls")
//	ClubDto mapToDto(Club club);
//
//	@Mapping(target = "members", ignore = true)
//	@Mapping(target = "meetings", ignore = true)
//	@Mapping(target = "polls", ignore = true)
//	@Mapping(target = "readBooks", ignore = true)
//	@Mapping(target = "futureReadBooks", ignore = true)
//	@Mapping(target = "contactLinkInfo", ignore = true)
//	Club mapToEntity(ClubDto clubDto);
//
//	List<ClubDto> mapToDto(List<Club> clubs);
//	List<Club> mapToEntity(List<ClubDto> clubDtoList);
//
//	@Mapping(target = "clubId", source = "club.clubId")
//	@Mapping(target = "hostId", source = "host.id")
//	MeetingDto mapMeetingToDto(Meeting meeting);
//
//	List<MeetingDto> mapMeetingToDto(List<Meeting> meetings);
//
//	@Mapping(target = "club", ignore = true)
//	@Mapping(target = "host", ignore = true)
//	@Mapping(target = "attendees", ignore = true)
//	Meeting mapMeetingToEntity(MeetingDto meetingDto);
//
//	List<Meeting> mapMeetingToEntity(List<MeetingDto> meetingDtoList);
//
//	@Mapping(target = "clubId", source = "club.clubId")
//	PollDto mapPollToDto(Poll poll);
//
//	List<PollDto> mapPollToDto(List<Poll> polls);
//
//	@Mapping(target = "club", ignore = true)
//	@Mapping(target = "votes", ignore = true)
//	Poll mapPollToEntity(PollDto pollDto);
//
//	List<Poll> mapPollToEntity(List<PollDto> pollDtoList);
//
//	@ObjectFactory
//	default Poll createPoll(PollDto dto) {
//		return switch (dto.getPollType()) {
//			case "YES_NO" -> new YesNoPoll();
//			case "MULTIPLE_CHOICE" -> new MultipleChoicePoll();
//			default -> throw new IllegalArgumentException("Unsupported poll type: " + dto.getPollType());
//		};
//	}
//
//	default List<UUID> mapMembersToIds(List<Member> members) {
//		return members.stream()
//				.map(Member::getId)
//				.collect(Collectors.toList());
//	}
//
//	default List<UUID> mapMeetingsToIds(List<Meeting> meetings) {
//		return meetings.stream()
//				.map(Meeting::getId)
//				.collect(Collectors.toList());
//	}
//
////	default List<UUID> mapPollsToIds(List<Poll> polls) {
////		return polls.stream()
////				.map(Poll::getPollId)  // Use getPollId() instead of getId()
////				.collect(Collectors.toList());
////	}
//
//}