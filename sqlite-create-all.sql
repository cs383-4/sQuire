create table user (
  id                            integer not null,
  username                      varchar(200),
  version                       integer not null,
  constraint pk_user primary key (id)
);

