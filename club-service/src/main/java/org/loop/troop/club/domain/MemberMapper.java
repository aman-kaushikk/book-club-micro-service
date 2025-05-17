package org.loop.troop.club.domain;

import org.loop.troop.club.domain.Member;
import org.loop.troop.club.domain.dto.MemberDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MemberMapper {

	@Mapping(target = "clubId", source = "club.clubId")
	MemberDto toDto(Member member);

	@Mapping(target = "club", ignore = true)
	Member toEntity(MemberDto memberDto);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateMemberFromDto(MemberDto dto, @MappingTarget Member entity);

}