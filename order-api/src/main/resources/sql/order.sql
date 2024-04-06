create table order_history
(
    id            bigint auto_increment
        primary key,
    order_id      bigint       not null,
    user_id       bigint       not null,
    price         decimal      not null,
    status        varchar(100) not null,
    packaged_yn   bit          not null,
    store_id      bigint       not null,
    payment_id    bigint       not null,
    created_by    varchar(100) not null,
    created_date  datetime     not null,
    modified_by   varchar(100) not null,
    modified_date datetime     not null
);

create table order_menu_history
(
    id               bigint auto_increment
        primary key,
    order_history_id bigint       not null,
    menu_id          bigint       not null,
    name             varchar(200) not null,
    price            decimal      not null,
    quantity         int          not null,
    created_by       varchar(100) not null,
    created_date     datetime     not null,
    modified_by      varchar(100) not null,
    modified_date    datetime     not null
);

create table order_menu_option_history
(
    id                    bigint auto_increment
        primary key,
    order_menu_history_id bigint       not null,
    option_id             bigint       not null,
    name                  varchar(200) not null,
    price                 decimal      not null,
    quantity              int          not null,
    created_by            varchar(100) not null,
    created_date          datetime     not null,
    modified_by           varchar(100) not null,
    modified_date         datetime     not null
);


create table orders
(
    id            bigint auto_increment
        primary key,
    payment_id    bigint       not null,
    store_id      bigint       not null,
    user_id       bigint       not null,
    price         decimal      not null,
    packaged_yn   bit          not null,
    status        varchar(100) not null,
    created_by    varchar(100) not null,
    created_date  datetime     not null,
    modified_by   varchar(100) not null,
    modified_date datetime     not null
);


create table order_menu
(
    id            bigint auto_increment
        primary key,
    order_id      bigint       not null,
    menu_id       bigint       not null,
    name          varchar(200) not null,
    price         decimal      not null,
    quantity      int          not null,
    created_by    varchar(100) not null,
    created_date  datetime     not null,
    modified_by   varchar(100) not null,
    modified_date datetime     not null
);

create table order_menu_option
(
    id                    bigint auto_increment
        primary key,
    order_menu_id bigint       not null,
    option_id             bigint       not null,
    name                  varchar(200) not null,
    price                 decimal      not null,
    quantity              int          not null,
    created_by            varchar(100) not null,
    created_date          datetime     not null,
    modified_by           varchar(100) not null,
    modified_date         datetime     not null
);

