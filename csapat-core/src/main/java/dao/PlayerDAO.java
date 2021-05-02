package dao;

import model.*;

import java.util.List;

public interface PlayerDAO {
    List<Player> findAll();
    List<Player> findByTeam(Team team);
    List<Player> findByTeam(int teamId);
    List<Player> findByRole(String role);

    List<Player> findByNationality(String nat);

    Player findById(Player player);
    Player findById(int playerId); /*ezt hivja meg a Playeres */

    Player save(Player player);
    void delete(Player player);


}
