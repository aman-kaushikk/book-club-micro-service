package org.loop.troop.club.domain;

import org.loop.troop.club.domain.dto.MemberDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/clubs/{clubId}/members")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping
	public ResponseEntity<MemberDto> createMember(@PathVariable UUID clubId, @RequestBody MemberDto memberDto) {

		MemberDto createdMember = memberService.createMember(clubId, memberDto);
		return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<MemberDto> getMember(@PathVariable UUID clubId, @PathVariable UUID memberId) {

		return ResponseEntity.ok(memberService.getMember(clubId, memberId));
	}

	@PutMapping("/{memberId}")
	public ResponseEntity<MemberDto> updateMember(@PathVariable UUID clubId, @PathVariable UUID memberId,
			@RequestBody MemberDto memberDto) {

		return ResponseEntity.ok(memberService.updateMember(clubId, memberId, memberDto));
	}

	@DeleteMapping("/{memberId}")
	public ResponseEntity<Void> deleteMember(@PathVariable UUID clubId, @PathVariable UUID memberId) {

		memberService.deleteMember(clubId, memberId);
		return ResponseEntity.noContent().build();
	}

}