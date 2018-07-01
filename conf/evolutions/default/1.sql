# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table role (
  id                            bigint auto_increment not null,
  role_short_name               varchar(255),
  role_display_name             varchar(255),
  info                          varchar(255),
  constraint pk_role primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  email                         varchar(255),
  name                          varchar(255),
  password                      varchar(255),
  remember_token                varchar(255),
  role                          varchar(255),
  user_disabled                 boolean default false not null,
  constraint pk_user primary key (id)
);


# --- !Downs

drop table if exists role;

drop table if exists user;

