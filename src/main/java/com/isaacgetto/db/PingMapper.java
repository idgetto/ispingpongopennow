package com.isaacgetto.db;

import com.isaacgetto.core.Ping;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class PingMapper implements ResultSetMapper<Ping> {

  @Override
  public Ping map(int i, ResultSet resultSet, StatementContext statementContext)
      throws SQLException {
    long id = resultSet.getLong("id");
    long time = resultSet.getLong("time");
    return new Ping(id, time);
  }
}
