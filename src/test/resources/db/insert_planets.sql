SET
REFERENTIAL_INTEGRITY FALSE;

TRUNCATE TABLE PLANET;

SET
REFERENTIAL_INTEGRITY TRUE;

INSERT INTO PLANET(ID, NAME, WEATHER, TERRAIN, MOVIE_APPEARANCES)
values (1, 'tatooine', 'arid', 'desert', 6);

INSERT INTO PLANET(ID, NAME, WEATHER, TERRAIN, MOVIE_APPEARANCES)
values (2, 'naboo', 'umid', 'forest', 3);