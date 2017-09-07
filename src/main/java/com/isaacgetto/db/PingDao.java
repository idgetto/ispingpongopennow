package com.isaacgetto.db;

import com.isaacgetto.core.Ping;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(PingMapper.class)
public interface PingDao {

  @SqlQuery("SELECT * FROM pings ORDER BY time DESC")
  List<Ping> getAll();

  @GetGeneratedKeys
  @SqlUpdate("INSERT INTO pings (time) VALUES (:time)")
  int insert(@BindBean Ping ping);
}
