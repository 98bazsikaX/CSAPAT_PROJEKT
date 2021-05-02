package dao;

import model.Player;
import model.Team;

import java.util.List;

public interface TeamDAO {

    List<Team> findAll();

    Team getByPlayer(Player player);
    Team getByPlayer(int id); /*mindig ezt hivjuk meg*/

    Team getById(Team team);
    Team getById(int id);

    List<Team> oldTeams(Player player);
    List<Team> oldTeams(int id);

    Team save(Team team);
    void delete(Team team);
}
