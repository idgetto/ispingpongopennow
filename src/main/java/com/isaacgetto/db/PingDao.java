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
  long DEFAULT_THRESHOLD_SECONDS = 30L;

  @SqlQuery("SELECT * FROM pings ORDER BY time DESC")
  List<Ping> getAll();

  @SqlQuery("SELECT * FROM pings WHERE time > :time ORDER BY time DESC")
  List<Ping> getAfter(@Bind("time") long time);

  default List<Ping> getRecent() {
    String thresholdSeconds = System.getenv("THRESHOLD_SECONDS");

    long numSeconds = DEFAULT_THRESHOLD_SECONDS;
    if (thresholdSeconds != null) {
      try {
        numSeconds = Long.parseLong(thresholdSeconds);
      } catch (NumberFormatException e) {
        System.out.printf("Couldn't parse %s as a long\n", thresholdSeconds);
        System.out.printf("Falling back to default threshold of %d seconds", DEFAULT_THRESHOLD_SECONDS);
      }
    }

    long earlier = Instant.now().minus(numSeconds, ChronoUnit.SECONDS).toEpochMilli();
    return getAfter(earlier);
  }

  default boolean anyRecent() {
    return !getRecent().isEmpty();
  }

  @GetGeneratedKeys
  @SqlUpdate("INSERT INTO pings (time) VALUES (:time)")
  int insert(@BindBean Ping ping);
}
