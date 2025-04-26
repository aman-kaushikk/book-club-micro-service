create table event_log (
    timestamp timestamp(6),
    event_id uuid not null,
    error_message varchar(255),
    event_type varchar(255) not null check (event_type in ('BOOK_CREATE','BOOK_UPDATE')),
    processing_status varchar(255) not null check (processing_status in ('PENDING','ONGOING','COMPLETED','ERROR')),
    routing_key varchar(255) not null,
    payload oid,
    primary key (event_id)
);