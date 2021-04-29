package dao;

import model.Player;
import model.Team;

import java.util.List;

public interface TeamDAO {

    List<Team> getByPlayerID(Player player);
    List<Team> getByPlayerID(int id); /*mindig ezt hivjuk meg*/

    Team getById(Team team);
    Team getById(int id);

    Team getByName(Team team);
    Team getByName(String name);

    Team save(Team team);
    void delete(Team team);
}
