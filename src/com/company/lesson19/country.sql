 CREATE SCHEMA country_storage;

 CREATE TABLE country_storage.employees
 (
     name                 CHARACTER VARYING(64),
     number_of_population INTEGER,
     area                 INTEGER,
    official_language    CHARACTER VARYING(64)

 );

 INSERT INTO country_storage.employees(name, number_of_population, area, official_language)
 VALUES ('Belarus', 9.5, 207.600, 'belorussian'),
        ('Germany', 83.24, 357.408, 'german'),
        ('France', 67.39, 551, 'french'),
        ('Spain', 47.35, 505, 'spanish'),
        ('Israel', 9, 22.145, 'hebrew'),
        ('Poland', 38, 312.679, 'polish'),
        ('Austria', 9, 83, 'german'),
        ('Greece', 10.7, 132, 'greek'),
        ('Italy', 60, 301.340, 'italian'),
        ('Ukraine', 42, 603, 'ukrainian');

 SELECT e.name, e.number_of_population
 FROM country_storage.employees AS e
 WHERE number_of_population > 50;

 SELECT e.name, e.area
 FROM country_storage.employees AS e
 ORDER BY e.area;

 SELECT replace(e.official_language, 'german', 'french')
 FROM country_storage.employees AS e;

 DELETE
 FROM country_storage.employees AS e
 WHERE e.number_of_population = (SELECT MAX(e.number_of_population) AS max_population
                                 FROM country_storage.employees AS e);

  SELECT SUM(s.area)
  FROM
     (SELECT e.area
      FROM country_storage.employees AS e
      ORDER BY e.area
          LIMIT 5) AS s;
