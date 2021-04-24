package dao;

import model.PlayableMap;

import java.util.List;

public interface MapDAO {
    List<PlayableMap> findAll();
    PlayableMap findByDbId(int id);
    PlayableMap findByIgId(String id);
    PlayableMap findByName(String name);

    PlayableMap save(PlayableMap map);
    void delete(PlayableMap map);
}
