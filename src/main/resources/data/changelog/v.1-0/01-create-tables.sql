    create table captcha_codes (
       id integer not null auto_increment,
        code varchar(255) not null,
        secret_code varchar(255) not null,
        time datetime not null,
        primary key (id)
    ) engine=InnoDB

GO
    
    create table global_settings (
       id integer not null auto_increment,
        code varchar(255) not null,
        name varchar(255) not null,
        value varchar(255) not null,
        primary key (id)
    ) engine=InnoDB
GO
    
    create table post (
       id integer not null auto_increment,
        is_active integer not null,
        mod_status integer not null,
        text varchar(255) not null,
        time datetime not null,
        title varchar(255) not null,
        view_count integer not null,
        moderator_id integer,
        user_id integer not null,
        primary key (id)
    ) engine=InnoDB
GO
    
    create table post_comments (
       id integer not null auto_increment,
        text varchar(255) not null,
        time datetime not null,
        parent_id integer,
        post_id integer not null,
        user_id integer not null,
        primary key (id)
    ) engine=InnoDB
GO
    
    create table post_votes (
       id integer not null auto_increment,
        time datetime not null,
        value integer not null,
        post_id integer not null,
        user_id integer not null,
        primary key (id)
    ) engine=InnoDB
GO
    
    create table tag2post (
       id integer not null auto_increment,
        post_id integer not null,
        tag_id integer not null,
        primary key (id)
    ) engine=InnoDB
GO
    
    create table tags (
       id integer not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB
GO
    
    create table users (
       id integer not null auto_increment,
        code varchar(255),
        email varchar(255) not null,
        is_moderator integer not null,
        name varchar(255) not null,
        password varchar(255) not null,
        photo varchar(255),
        reg_time datetime not null,
        primary key (id)
    ) engine=InnoDB
GO
    
    alter table post 
       add constraint FKdo3e7podvbygqlilxddgo13be 
       foreign key (moderator_id) 
       references users (id)
GO
    
    alter table post 
       add constraint FK7ky67sgi7k0ayf22652f7763r 
       foreign key (user_id) 
       references users (id)
GO
    
    alter table post_comments 
       add constraint FKc3b7s6wypcsvua2ycn4o1lv2c 
       foreign key (parent_id) 
       references post_comments (id)
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