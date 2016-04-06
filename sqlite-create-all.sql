create table o_user (
  id                            integer not null,
  username                      varchar(200),
  password                      varchar(200),
  version                       integer not null,
  constraint pk_o_user primary key (id)
);
