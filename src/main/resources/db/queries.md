#### 1. Запросы с WHERE условием.

Показать названия и даты выхода фильмов, режисером которых является George Lucas:
```
SELECT title, release_date 
    FROM movie_rating.movie 
    WHERE director = 'George Lucas';
```

![Where reques 1](https://github.com/z1max/movierating/blob/master/src/main/resources/sql-images/where1.png)

Показать названия и продолжительность фильмов, которые длятся более 2.5 чвсов (160 минут):
```
SELECT title, runtime 
    FROM movie 
    WHERE runtime > 160;
```

![Where request 2](https://github.com/z1max/movierating/blob/master/src/main/resources/sql-images/where2.png)

#### 2. Запросы с применением встроенных функций.

Занести в таблтцу нового пользователя с текущей датой регистрации и хеш-кодом пароля:
```
INSERT INTO movie_rating.user (username, email, password, registered, status, enabled) VALUES 
    ('new user', 'newemail@gmail.com', MD5('newpass'), CURRENT_DATE(), 'New', 1);
```

![Build in function 1](https://github.com/z1max/movierating/blob/master/src/main/resources/sql-images/buildin_func1.png)

Вывести пользователей в виде JSON объекта:
```
SELECT JSON_OBJECT('id', id, 'username', username, 'email', email, 'password', password)
    AS 'json_user'
    FROM movie_rating.user;
```

![Build in function 2](https://github.com/z1max/movierating/blob/master/src/main/resources/sql-images/buildin_func2.png)

#### 3. Запросы на соединение таблиц.

Вывести пользователей вместе с набором ролей:
```
SELECT username, group_concat(role SEPARATOR ',') AS roles
    FROM movie_rating.user 
    JOIN movie_rating.user_role ON id = user_id
    GROUP BY username;
```

![Join 1](https://github.com/z1max/movierating/blob/master/src/main/resources/sql-images/join1.png)

Вывести все фильмы вместе со средней оценкой:
```
SELECT title, AVG(rating) 
    FROM movie_rating.movie 
    LEFT JOIN movie_rating.rating ON id = movie_id
    GROUP BY title;
```

![Join 2](https://github.com/z1max/movierating/blob/master/src/main/resources/sql-images/join2.png)tps://github.com/z1max/movierating/blob/master/src/main/resources/sql-images/where2.png)