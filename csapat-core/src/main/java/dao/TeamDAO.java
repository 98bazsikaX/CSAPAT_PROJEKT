package dao;

import model.Player;
import model.Team;

import java.util.List;

public interface TeamDAO {

    List<Team> findAll();

    List<Team> getByPlayerID(Player player);
    List<Team> getByPlayerID(int id); /*mindig ezt hivjuk meg*/

    Team getById(Team team);
    Team getById(int id);

    Team save(Team team);
    void delete(Team team);
}
