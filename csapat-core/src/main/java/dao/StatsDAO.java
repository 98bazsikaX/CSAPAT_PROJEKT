package dao;

import model.Match;
import model.Player;
import model.PlayerStatistics;

import java.util.List;

public interface StatsDAO {
    List<PlayerStatistics> findAll();
    List<PlayerStatistics> findByMatch(Match match);
    List<PlayerStatistics> findByMatch(int id);
    List<PlayerStatistics> findByPlayer(Player player);
    List<PlayerStatistics> findByPlayer(int id);

    PlayerStatistics save(PlayerStatistics stat);
    void delete(PlayerStatistics stat);

}
