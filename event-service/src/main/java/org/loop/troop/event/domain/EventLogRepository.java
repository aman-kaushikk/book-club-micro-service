package org.loop.troop.event.domain;

import org.loop.troop.event.domain.enums.EventProcessingStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface EventLogRepository extends CrudRepository<EventLog, UUID> {

	List<EventLog> findByProcessingStatus(EventProcessingStatus status);
}
