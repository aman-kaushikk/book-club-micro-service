package org.loop.troop.event.domain;

import org.loop.troop.event.domain.enums.EventProcessingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface EventLogRepository extends JpaRepository<EventLog, UUID> {

	@Query("select e from EventLog e where e.processingStatus = ?1 or e.processingStatus = ?2")
	List<EventLog> findEventLogByProcessStatus(EventProcessingStatus processStatus,
			EventProcessingStatus processStatusTwo);

}
