    alter table post
       drop
       foreign key FK1mpebp1ayl0twrwm7ruiof778

GO

    alter table post_comments
       drop
       foreign key FKmws3vvhl5o4t7r7sajiqpfe0b

GO

    alter table post_comments
       drop
       foreign key FKsnxoecngu89u3fh4wdrgf0f2g

GO

    alter table post_votes
       drop
       foreign key FKkii0lkyj3a3jj95vgym33ho4b

GO

    alter table post_votes
       drop
       foreign key FK9q09ho9p8fmo6rcysnci8rocc

GO

    alter table tag2post
       drop
       foreign key FK2nnf4bm2w83lqajy78ib6eerb

GO

    alter table tag2post
       drop
       foreign key FKjou6suf2w810t2u3l96uasw3r

GO

    drop table if exists captcha_codes

GO

    drop table if exists global_settings

GO

    drop table if exists hibernate_sequence

GO

    drop table if exists post

GO

    drop table if exists post_comments

GO

    drop table if exists post_votes

GO

    drop table if exists tag2post

GO

    drop table if exists tags

GO

    drop table if exists users

GO
