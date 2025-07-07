create table if not exists product_item
(
    id   int AUTO_INCREMENT primary key,
    name varchar(8) not null,
    pid  int        not null
);