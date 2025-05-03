package org.loop.troop.club.domain;

import org.loop.troop.club.domain.dto.ClubDto;
import org.loop.troop.club.domain.dto.CreateClubRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookClubService {

	private final ClubRepository clubRepository;

	private final ClubMapper clubMapper;

	@Autowired
	public BookClubService(ClubRepository clubRepository, ClubMapper clubMapper) {
		this.clubRepository = clubRepository;
		this.clubMapper = clubMapper;
	}

	public ClubDto createBookClub(CreateClubRequest createClubRequest) {
		ClubDto bookClubDto = clubMapper.createClubTo(createClubRequest);
		Club club = clubMapper.mapToEntity(bookClubDto);
		Club savedClub = clubRepository.save(club);
		return clubMapper.mapToDto(savedClub);
	}

	public ClubDto getBookClub(UUID clubId) {
		Club club = clubRepository.findById(clubId)
			.orElseThrow(() -> new ResourceNotFoundException("BookClub not found"));
		return clubMapper.mapToDto(club);
	}

	public void deleteBookClub(UUID clubId) {
		Club club = clubRepository.findById(clubId)
			.orElseThrow(() -> new ResourceNotFoundException("BookClub not found"));
		clubRepository.delete(club);
	}

	public ClubDto updateBookClub(UUID clubId, ClubDto bookClubDto) {
		Club club = clubRepository.findById(clubId)
			.orElseThrow(() -> new ResourceNotFoundException("BookClub not found"));
		club.setName(bookClubDto.getName());
		Club updatedClub = clubRepository.save(club);
		return clubMapper.mapToDto(updatedClub);
	}

}
