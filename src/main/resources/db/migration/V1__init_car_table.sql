drop table if exists CAR;
create table CAR
(
    ID                  int             primary key auto_increment,
    BRAND               varchar(255)    not null,
    COLOR               varchar(255)    not null,
    DISPLACEMENT_VOLUME int             not null,
    ENGINE              varchar(255)    not null,
    ENGINE_POWER        double          not null,
    FUEL                varchar(255)    not null,
    GENERATION          varchar(255)    not null,
    MODEL               varchar(255)    not null,
    ODOMETER            int             not null,
    SEATS               int             not null,
    TYPE_Car            varchar(255)    not null,
    YEAR_OF_PRODUCTION  int             not null
);