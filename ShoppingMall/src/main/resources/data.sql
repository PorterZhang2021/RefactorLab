insert into product_item(id, name, pid)
values (1, '商城', 0),
       (2, '电脑', 1),
       (3, '书籍', 1),
       (4, '台式电脑', 2),
       (5, '笔记本电脑', 2),
       (6, '游戏电脑', 4),
       (7, '办公电脑 ', 4),
       (8, '教育类', 3),
       (9, '科普类', 3),
       (10, '九年义务教育书籍', 8);

ALTER TABLE product_item ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM product_item);