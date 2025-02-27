package com.validator.config;

import com.validator.resources.OrcidResource;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component
public interface AppComponent {
    OrcidResource getOrcidResource();
}
