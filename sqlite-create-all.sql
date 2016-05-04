create table o_project (
  id                            integer not null,
  owner_id                      integer,
  name                          varchar(255),
  description                   varchar(255),
  primary_file_id               integer,
  project_uuid                  varchar(255),
  version                       integer not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint uq_o_project_primary_file_id unique (primary_file_id),
  constraint pk_o_project primary key (id),
  foreign key (owner_id) references o_user (id) on delete restrict on update restrict,
  foreign key (primary_file_id) references o_project_file (id) on delete restrict on update restrict
);

create table o_project_file (
  id                            integer not null,
  project_id                    integer,
  name                          varchar(255),
  version                       integer not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_o_project_file primary key (id),
  foreign key (project_id) references o_project (id) on delete restrict on update restrict
);

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
  email                         varchar(255) not null,
  version                       integer not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint uq_o_user_username unique (username),
  constraint uq_o_user_email unique (email),
  constraint pk_o_user primary key (id)
);

create table o_works_on (
  id                            integer not null,
  uid_id                        integer,
  pid_id                        integer,
  version                       integer not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint uq_o_works_on_pid_id unique (pid_id),
  constraint pk_o_works_on primary key (id),
  foreign key (uid_id) references o_user (id) on delete restrict on update restrict,
  foreign key (pid_id) references o_project (id) on delete restrict on update restrict
);

