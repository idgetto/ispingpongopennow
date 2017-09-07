package com.isaacgetto;

import com.isaacgetto.db.PingDao;
import com.isaacgetto.resources.PingResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class IsPingPongOpenNowApplication extends Application<IsPingPongOpenNowConfiguration> {

  public static void main(final String[] args) throws Exception {
    new IsPingPongOpenNowApplication().run(args);
  }

  @Override
  public String getName() {
    return "IsPingPongOpenNow";
  }

  @Override
  public void initialize(final Bootstrap<IsPingPongOpenNowConfiguration> bootstrap) {
    bootstrap.setConfigurationSourceProvider(
        new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
            new EnvironmentVariableSubstitutor()));

    bootstrap.addBundle(new MigrationsBundle<IsPingPongOpenNowConfiguration>() {
      @Override
      public PooledDataSourceFactory getDataSourceFactory(
          IsPingPongOpenNowConfiguration isPingPongOpenNowConfiguration) {
        return isPingPongOpenNowConfiguration.getDataSourceFactory();
      }
    });
  }

  @Override
  public void run(final IsPingPongOpenNowConfiguration configuration,
      final Environment environment) {
    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory
        .build(environment, configuration.getDataSourceFactory(), "ispingpongopennow");

    final PingDao pingDao = jdbi.onDemand(PingDao.class);

    final PingResource pingResource = new PingResource(pingDao);
    environment.jersey().register(pingResource);
  }
}
