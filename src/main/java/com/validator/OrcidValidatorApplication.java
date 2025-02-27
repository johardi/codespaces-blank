package com.validator;

import com.validator.config.AppComponent;
import com.validator.config.DaggerAppComponent;
import com.validator.health.ApplicationHealthCheck;
import com.validator.resources.OrcidResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;

public class OrcidValidatorApplication extends Application<OrcidValidatorConfiguration> {
    public static void main(String[] args) throws Exception {
        new OrcidValidatorApplication().run(args);
    }

    @Override
    public void run(OrcidValidatorConfiguration configuration, Environment environment) {
        AppComponent appComponent = DaggerAppComponent.create();
        
        environment.jersey().register(appComponent.getOrcidResource());
        environment.healthChecks().register("application", new ApplicationHealthCheck());
    }
}
