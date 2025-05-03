package org.loop.troop.club.domain;

import org.loop.troop.club.domain.dto.ClubDto;
import org.loop.troop.club.domain.dto.MeetingDto;
import org.loop.troop.club.domain.dto.PollDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
interface ClubMapper {

	ClubMapper INSTANCE = Mappers.getMapper(ClubMapper.class);

	ClubDto mapToDto(Club club);

	Club mapToEntity(ClubDto clubDto);

	List<ClubDto> mapToDto(List<Club> clubs);

	List<Club> mapToEntity(List<ClubDto> clubDtoList);

	MeetingDto mapMeetingToDto(Meeting meeting);

	List<MeetingDto> mapMeetingToDto(List<Meeting> meetings);

	Meeting mapMeetingToEntity(MeetingDto meetingDto);

	List<Meeting> mapMeetingToEntity(List<MeetingDto> meetingDtoList);

	PollDto mapPollToDto(Poll poll);

	List<PollDto> mapPollToDto(List<Poll> polls);

	Poll mapPollToEntity(PollDto pollDto);

	List<Poll> mapPollToEntity(List<PollDto> pollDtoList);

}