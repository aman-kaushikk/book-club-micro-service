package org.loop.troop.club.domain;

import org.loop.troop.club.domain.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository,
                         ClubRepository clubRepository,
                         MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.clubRepository = clubRepository;
        this.memberMapper = memberMapper;
    }

    @Transactional
    public MemberDto createMember(UUID clubId, MemberDto memberDto) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new ResourceNotFoundException("Club not found"));

        Member member = memberMapper.toEntity(memberDto);
        member.setClub(club);

        Member savedMember = memberRepository.save(member);
        return memberMapper.toDto(savedMember);
    }

    public MemberDto getMember(UUID clubId, UUID memberId) {
        return memberRepository.findByClub_ClubIdAndId(clubId, memberId)
                .map(memberMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
    }

    @Transactional
    public MemberDto updateMember(UUID clubId, UUID memberId, MemberDto memberDto) {
        Member existingMember = memberRepository.findByClub_ClubIdAndId(clubId, memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        memberMapper.updateMemberFromDto(memberDto, existingMember);
        Member updatedMember = memberRepository.save(existingMember);
        return memberMapper.toDto(updatedMember);
    }

    @Transactional
    public void deleteMember(UUID clubId, UUID memberId) {
        Member member = memberRepository.findByClub_ClubIdAndId(clubId, memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
        memberRepository.delete(member);
    }
}