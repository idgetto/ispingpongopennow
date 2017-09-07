package com.isaacgetto.core;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}
