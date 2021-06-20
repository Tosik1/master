    create table captcha_codes (
       id integer not null,
        code varchar(255),
        secret_code varchar(255),
        time datetime,
        primary key (id)
    ) engine=InnoDB

GO

    create table global_settings (
       id integer not null,
        code varchar(255),
        name varchar(255),
        value varchar(255),
        primary key (id)
    ) engine=InnoDB

GO

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

GO

    insert into hibernate_sequence values ( 1 )

GO

    insert into hibernate_sequence values ( 1 )

GO

    insert into hibernate_sequence values ( 1 )

GO

    insert into hibernate_sequence values ( 1 )

GO

    insert into hibernate_sequence values ( 1 )

GO

    insert into hibernate_sequence values ( 1 )

GO

    insert into hibernate_sequence values ( 1 )

GO

    create table post (
       id integer not null,
        is_active integer not null,
        mod_status integer,
        text varchar(255),
        time datetime,
        title varchar(255),
        view_count integer not null,
        author_id integer,
        primary key (id)
    ) engine=InnoDB

GO

    create table post_comments (
       id integer not null,
        text varchar(255),
        time datetime,
        post_id integer,
        user_id integer,
        primary key (id)
    ) engine=InnoDB

GO

    create table post_votes (
       id integer not null,
        time datetime,
        value integer not null,
        post_id integer,
        user_id integer,
        primary key (id)
    ) engine=InnoDB

GO

    create table tag2post (
       id integer not null,
        post_id integer,
        tag_id integer,
        primary key (id)
    ) engine=InnoDB

GO

    create table tags (
       id integer not null,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB

GO

    create table users (
       id integer not null auto_increment,
        code varchar(255),
        email varchar(255),
        is_moderator integer not null,
        name varchar(255),
        password varchar(255),
        photo varchar(255),
        reg_time datetime,
        primary key (id)
    ) engine=InnoDB

GO

    alter table post
       add constraint FK1mpebp1ayl0twrwm7ruiof778
       foreign key (author_id)
       references users (id)

GO

    alter table post_comments
       add constraint FKmws3vvhl5o4t7r7sajiqpfe0b
       foreign key (post_id)
       references post (id)

GO

    alter table post_comments
       add constraint FKsnxoecngu89u3fh4wdrgf0f2g
       foreign key (user_id)
       references users (id)

GO

    alter table post_votes
       add constraint FKkii0lkyj3a3jj95vgym33ho4b
       foreign key (post_id)
       references post (id)

GO

    alter table post_votes
       add constraint FK9q09ho9p8fmo6rcysnci8rocc
       foreign key (user_id)
       references users (id)

GO

    alter table tag2post
       add constraint FK2nnf4bm2w83lqajy78ib6eerb
       foreign key (post_id)
       references post (id)

GO

    alter table tag2post
       add constraint FKjou6suf2w810t2u3l96uasw3r
       foreign key (tag_id)
       references tags (id)

GO
