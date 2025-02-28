package com.validator.config;

import com.validator.service.OrcidValidator;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {
    @Provides
    @Singleton
    OrcidValidator provideOrcidValidator() {
        return new OrcidValidator();
    }
}
