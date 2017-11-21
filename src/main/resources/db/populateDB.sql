DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, dateTime, description, calories) VALUES
  ( 100000, '2017-11-19 9:00', 'Завтрак', 500),
  ( 100000, '2017-11-19 13:00', 'Обед', 1000),
  ( 100000,  '2017-11-19 20:00', 'Ужин', 500),
  ( 100001, '2017-11-20 9:00', 'Завтрак', 500),
  ( 100001, '2017-11-19 13:00', 'Обед', 1000),
  ( 100001, '2017-11-19 13:00', 'Обед', 1000);