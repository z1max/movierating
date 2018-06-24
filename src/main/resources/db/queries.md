#### 1. Запросы с WHERE условием

Показать названия и даты выхода фильмов, режисером которых является George Lucas:
```
SELECT title, release_date 
	FROM movie_rating.movie 
    WHERE director = 'George Lucas';
```

![alt text](sql-images/where1.png)

Показать названия и продолжительность фильмовб которые длятся более 2.5 чвсов (160 минут):
```
SELECT title, runtime 
	FROM movie 
    WHERE runtime > 160;
```

![alt text](sql-images/where2.png)