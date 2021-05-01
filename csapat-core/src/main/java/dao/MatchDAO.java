package dao;

import model.Match;
import model.Team;
import model.Tournament;

import java.util.List;

public interface MatchDAO {
    List<Match> findAll();
    List<Match> findByTeam(Team team);
    List<Match> findByTeam(int id);

    List<Match> findByTournament(Tournament tournament);
    List<Match> findByTournament(int id);

    Match findByID(Match match);
    Match findByID(int id);

    Match save(Match match);
    void delete(Match match);

}
