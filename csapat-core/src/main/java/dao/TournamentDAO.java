package dao;

import model.Match;
import model.Player;
import model.Team;
import model.Tournament;

import java.util.List;

public interface TournamentDAO {
    List<Tournament> findAll();

    Tournament findByMatchID(Match match);
    Tournament findByMatchID(int id);

    List<Tournament> findByWinner(Team team);
    List<Tournament> findByWinner(int id);

    Tournament save(Tournament tournament);
    void delete(Tournament tournament);

}
