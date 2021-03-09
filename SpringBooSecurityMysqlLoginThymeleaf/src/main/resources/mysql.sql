  create table member (
       id bigint not null  AUTO_INCREMENT,
        age integer,
        is_married boolean,
        member_id varchar(255),
        password varchar(255),
        primary key (id)
    );