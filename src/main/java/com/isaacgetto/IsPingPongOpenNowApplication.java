package com.isaacgetto;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: application initialization
    }

    @Override
    public void run(final IsPingPongOpenNowConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
