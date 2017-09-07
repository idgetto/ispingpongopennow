package com.isaacgetto.db;

import com.isaacgetto.core.Ping;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(PingMapper.class)
public interface PingDao {

  @SqlQuery("SELECT * FROM pings ORDER BY time DESC")
  List<Ping> getAll();

  @SqlQuery("SELECT * FROM pings WHERE time > :time ORDER BY time DESC")
  List<Ping> getAfter(@Bind("time") long time);

  default List<Ping> getRecent() {
    long fiveMinutesAgo = Instant.now().minus(5, ChronoUnit.MINUTES).toEpochMilli();
    return getAfter(fiveMinutesAgo);
  }

  default boolean anyRecent() {
    return !getRecent().isEmpty();
  }

  @GetGeneratedKeys
  @SqlUpdate("INSERT INTO pings (time) VALUES (:time)")
  int insert(@BindBean Ping ping);
}
