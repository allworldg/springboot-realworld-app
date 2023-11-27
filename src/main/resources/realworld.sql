create table user
(
    id       bigint primary key,
    email    varchar(255) unique,
    username varchar(255) unique,
    password varchar(255),
    bio      text,
    image    varchar(511)
);

create table article
(
    id          bigint primary key,
    user_id     bigint,
    title       varchar(255),
    slug        varchar(255) unique,
    description text,
    body        text,
    created_at  timestamp not null default current_timestamp,
    updated_at  timestamp not null default current_timestamp
);

create table comment
(
    id         bigint primary key,
    user_id    bigint,
    body       text,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create table tag
(
    id   bigint primary key,
    name varchar(255)
);

create table user_follow_user
(
    user_id        bigint not null,
    follow_user_id bigint not null
);

create table tag_article
(
    tag_id     bigint not null,
    article_id bigint not null
);


