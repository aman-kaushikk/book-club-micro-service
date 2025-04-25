create table book (
    bookmarked integer,
    page_count integer,
    rating float(53),
    created_at timestamp(6),
    updated_at timestamp(6),
    book_id uuid not null,
    created_by uuid,
    updated_by uuid,
    author varchar(255) not null,
    book_status varchar(255) not null check (book_status in ('ONGOING','COMPLETED','UNKNOWN')),
    book_url varchar(255) not null,
    description TEXT,
    title varchar(255) not null,
    primary key (book_id)
);

create table book_buy_link (
    book_id_fk uuid not null,
    url varchar(255) not null,
    vendor varchar(255) not null
);

create table book_club (
    book_id_fk uuid not null,
    club_id uuid
);

create table book_genre (
    book_id_fk uuid not null,
    genre_id varchar(255)
);

create table book_tag (
    book_id_fk uuid not null,
    tag_id varchar(255)
);

create table review (
    inappropriate boolean,
    star float(53) not null,
    created_at timestamp(6),
    book_id_fk uuid not null,
    id uuid not null,
    user_id uuid not null,
    review_description TEXT not null,
    review_title varchar(255) not null,
    user_profile_url varchar(255) not null,
    primary key (id)
);