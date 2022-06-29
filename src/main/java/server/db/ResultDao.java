package server.db;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ResultDao implements IResultDao{
    @Override
    public void add(GameResult result) {
        Session _session = DbConnection.getSession();
        Transaction _transaction = _session.beginTransaction();
        _session.persist(result);
        _transaction.commit();
        _session.close();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public GameResult getEmployee(int id) {
        return null;
    }

    @Override
    public List<GameResult> getResults() {
        Session _session = DbConnection.getSession();
        List<GameResult> _result = _session.createQuery("FROM GameResult ", GameResult.class).list();
        _session.close();
        return _result;
    }

    @Override
    public void update(GameResult emp) {

    }
}
