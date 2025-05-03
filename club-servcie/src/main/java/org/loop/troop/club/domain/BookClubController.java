package org.loop.troop.club.domain;

import org.loop.troop.club.domain.dto.ClubDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/club")
class BookClubController {

	private final BookClubService bookClubService;

	@Autowired
	BookClubController(BookClubService bookClubService) {
		this.bookClubService = bookClubService;
	}

	@PostMapping
	ResponseEntity<ClubDto> createClub(@RequestBody ClubDto ClubDto) {
		ClubDto createdBookClub = bookClubService.createBookClub(ClubDto);
		return new ResponseEntity<>(createdBookClub, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	ResponseEntity<ClubDto> getClub(@PathVariable UUID id) {
		ClubDto ClubDto = bookClubService.getBookClub(id);
		return ClubDto != null ? ResponseEntity.ok(ClubDto) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteClub(@PathVariable UUID id) {
		bookClubService.deleteBookClub(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	ResponseEntity<ClubDto> updateClub(@PathVariable UUID id, @RequestBody ClubDto ClubDto) {
		ClubDto updatedBookClub = bookClubService.updateBookClub(id, ClubDto);
		return updatedBookClub != null ? ResponseEntity.ok(updatedBookClub) : ResponseEntity.notFound().build();
	}

}
