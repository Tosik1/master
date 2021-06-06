create table captcha_codes (id integer not null, code varchar(255), secret_code varchar(255), time datetime, primary key (id)) engine=InnoDB
create table global_settings (id integer not null, code tinyblob, name tinyblob, value tinyblob, primary key (id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
create table post (id integer not null, is_active tinyblob, moderator_id integer not null, text longtext, time datetime, title tinyblob, user_id integer not null, view_count integer not null, primary key (id)) engine=InnoDB
create table post_comments (id integer not null, parent_id integer not null, post_id integer not null, text varchar(255), time datetime, user_id integer not null, primary key (id)) engine=InnoDB
create table post_votes (id integer not null, post_id integer not null, time datetime, user_id integer not null, value integer not null, primary key (id)) engine=InnoDB
create table tag2post (id integer not null, post_id integer not null, tag_id integer not null, primary key (id)) engine=InnoDB
create table tags (id integer not null, name tinyblob, primary key (id)) engine=InnoDB
create table user (
    id integer not null,
    code varchar(255),
    email varchar(255) not null,
    is_moderator integer not null,
    name varchar(255) not null,
    password varchar(255) not null,
    photo longtext,
    reg_time datetime not null,
    primary key (id)) engine=InnoDB
