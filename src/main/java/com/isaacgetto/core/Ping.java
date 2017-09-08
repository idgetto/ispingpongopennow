package com.isaacgetto.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Ping {
  private long id;
  private long time;

  public Ping() {
    this.time = System.currentTimeMillis();
  }

  public Ping(long id, long time) {
    this.id = id;
    this.time = time;
  }

  @JsonProperty
  public long getId() {
    return id;
  }

  @JsonProperty
  public long getTime() {
    return time;
  }

  @JsonProperty
  public String getDate() {
    ZoneId zoneId = ZoneId.of("America/New_York");
    LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(getTime()), zoneId);
    return dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy h:mm:s a"));
  }
}
