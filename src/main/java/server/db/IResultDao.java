package server.db;

import java.sql.SQLException;
import java.util.List;

public interface IResultDao {
    void add(GameResult emp)
            throws SQLException;
    void delete(int id)
            throws SQLException;
    GameResult getEmployee(int id)
            throws SQLException;
    List<GameResult> getResults()
            throws SQLException;
    void update(GameResult emp)
            throws SQLException;
}
