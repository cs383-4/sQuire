create table o_session (
  id                            integer not null,
  user_id                       integer,
  expires                       timestamp not null,
  token                         varchar(40) not null,
  version                       integer not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint uq_o_session_token unique (token),
  constraint pk_o_session primary key (id),
  foreign key (user_id) references o_user (id) on delete restrict on update restrict
);

create table o_user (
  id                            integer not null,
  username                      varchar(255) not null,
  password                      varchar(255) not null,
  version                       integer not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint uq_o_user_username unique (username),
  constraint pk_o_user primary key (id)
);

create table o_project_info (
  id                            integer not null,
  author_username               varchar(255) not null,
  version                       integer not null,
  files                         varchar(255) not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_o_project_info primary key (id)
);

create table o_project_file (
  id                            integer not null,
  file                          blob,
  constraint pk_o_project_file primary key (id)
);
