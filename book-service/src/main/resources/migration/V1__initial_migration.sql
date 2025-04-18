create table book_buy_links (
    book_id uuid not null,
    url varchar(255) not null,
    vendor varchar(255) not null
);

create table book_clubs (
    book_id uuid not null,
    club_id uuid
);

create table books (
    page_count integer,
    rating float,
    created_at timestamp(6),
    updated_at timestamp(6),
    book_id uuid not null,
    created_by uuid,
    updated_by uuid,
    author varchar(255) not null,
    book_url varchar(255) not null,
    description TEXT,
    title varchar(255) not null,
    primary key (book_id)
);

create table reviews (
    inappropriate boolean,
    stars integer,
    book_id uuid not null,
    id uuid not null,
    user_id uuid,
    review_description TEXT,
    user_profile_url varchar(255),
    primary key (id)
);