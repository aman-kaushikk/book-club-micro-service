package org.loop.troop.club.domain;

import org.loop.troop.club.domain.dto.ClubDto;
import org.loop.troop.club.domain.dto.MeetingDto;
import org.loop.troop.club.domain.dto.PollDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClubMapper {

    ClubMapper INSTANCE = Mappers.getMapper(ClubMapper.class);

    @Mapping(source = "members", target = "memberIds")
    ClubDto mapToDto(Club club);

    List<ClubDto> mapToDto(List<Club> clubs);
    @Mapping(source = "memberIds", target = "members")
    Club mapToEntity(ClubDto clubDto);

    List<Club> mapToEntity(List<ClubDto> clubDtoList);

    MeetingDto mapMeetingToDto(Meeting meeting);

    List<MeetingDto> mapMeetingToDto(List<Meeting> meetings);

    Meeting mapMeetingToEntity(MeetingDto meetingDto);

    List<Meeting> mapMeetingToEntity(List<MeetingDto> meetingDtoList);

    PollDto mapPollToDto(Poll poll);

    List<PollDto> mapPollToDto(List<Poll> polls);

    Poll mapPollToEntity(PollDto pollDto);

    List<Poll> mapPollToEntity(List<PollDto> pollDtos);
}