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
  ativo                     boolean,
  constraint pk_fornecedor primary key (id))
;




# --- !Downs

drop table if exists fornecedor cascade;

