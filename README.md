# CSAPAT PROJEKT
Alkalmazásfejlesztés I projektfeladat

##csapat-core
A csapat core packageban találhatőak a model osztályok illetve az adat elérést szolgáló DAO osztályok.

##csapat-desktop
A program elindítása után a kezdő lapon az adatbázisban lévő játékosok láthatóak, az oldalsó gombokkal más menükbe lehet átmenni, melyekből visszalehet térni a fő oldalra. Mindegyik menü esetén van az ott található adatok módosítására, kivétel a csapat-előzmények táblán ahol egy játékos nevének kiválasztása után láthatjuk hogy ezelőtt milyen csapatokban volt.

##csapat-webapp[webapp]
A webapp főoldalán megtekinthetőek az adatbázisban lévő meccsek alap adatai, illetve átlehet menni a bejelentkezési oldalra.
Bejelentkezni csak olyan felhasználó tud akit eddig hozzáadtak az adatbázishoz, mivel az adatbázishoz hozzáadáskor nincs beállítva email cím illetve jelszó, ezért az első bejelentkezés során lehet ezeket megadni.
A webappban bejelentkezett felhasználók számára lehetséges új meccseket felvinni. A nem bejelentkezett felhasználók viszont csak eme meccsek eredményei tudják megjeleníteni.

##Trigger az adatbázisban

Az adatbázisban található egy trigger az USERS táblán, de  az sqlite nálam néha elfelejti létezését.

```sql
CREATE TRIGGER IF NOT EXISTS WAS_IN_TEAM_TRIGGER
    AFTER UPDATE
    ON USERS
    WHEN old.team_id != new.team_id AND old.team_id IS NOT NULL
    BEGIN
        INSERT INTO was_in_table(player_id, team_id) VALUES (id,new.team_id);
    END;
```