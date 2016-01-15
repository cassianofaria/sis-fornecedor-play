# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table fornecedor (
  id                        bigserial not null,
  nome                      varchar(255),
  email                     varchar(255),
  telefone                  varchar(255),
  dt_cadastro               timestamp,
  dt_atualizacao            timestamp,
  segmento_id               bigint,
  ativo                     boolean,
  constraint pk_fornecedor primary key (id))
;

create table produto (
  id                        bigserial not null,
  nome                      varchar(255),
  constraint pk_produto primary key (id))
;

create table segmento (
  id                        bigserial not null,
  nome                      varchar(255),
  constraint pk_segmento primary key (id))
;

create table usuario (
  id                        bigserial not null,
  nome                      varchar(255),
  senha                     varchar(255),
  constraint pk_usuario primary key (id))
;

alter table fornecedor add constraint fk_fornecedor_segmento_1 foreign key (segmento_id) references segmento (id);
create index ix_fornecedor_segmento_1 on fornecedor (segmento_id);



# --- !Downs

drop table if exists fornecedor cascade;

drop table if exists produto cascade;

drop table if exists segmento cascade;

drop table if exists usuario cascade;

