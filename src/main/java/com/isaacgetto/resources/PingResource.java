package com.isaacgetto.resources;

import com.isaacgetto.core.Ping;
import com.isaacgetto.db.PingDao;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ping")
@Produces(MediaType.APPLICATION_JSON)
public class PingResource {
  private final PingDao pingDao;

  public PingResource(PingDao pingDao) {
    this.pingDao = pingDao;
  }

  @GET
  public List<Ping> getAll() {
    return pingDao.getAll();
  }

  @GET
  @Path("/recent")
  public boolean anyRecent() {
    return pingDao.anyRecent();
  }

  @POST
  public Ping ping(@NotNull Ping ping) {
    long id = pingDao.insert(ping);
    return new Ping(id, ping.getTime());
  }
}
