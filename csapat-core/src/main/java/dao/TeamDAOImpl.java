package dao;

import model.Player;
import model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamDAOImpl implements TeamDAO{
    @Override
    public List<Team> getByPlayerID(Player player) {
        return this.getByPlayerID(player.getId());
    }

    @Override
    public List<Team> getByPlayerID(int id) {
        return new ArrayList<Team>();
    }

    @Override
    public Team getById(Team team) {
        return null;
    }

    @Override
    public Team getById(int id) {
        return null;
    }

    @Override
    public Team getByName(Team team) {
        return null;
    }

    @Override
    public Team getByName(String name) {
        return null;
    }

    @Override
    public Team save(Team team) {
        return null;
    }

    @Override
    public void delete(Team team) {

    }
}
