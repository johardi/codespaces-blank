package com.validator.config;

import com.validator.service.OrcidValidator;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AppModule_ProvideOrcidValidatorFactory implements Factory<OrcidValidator> {
  private final AppModule module;

  public AppModule_ProvideOrcidValidatorFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public OrcidValidator get() {
    return provideOrcidValidator(module);
  }

  public static AppModule_ProvideOrcidValidatorFactory create(AppModule module) {
    return new AppModule_ProvideOrcidValidatorFactory(module);
  }

  public static OrcidValidator provideOrcidValidator(AppModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideOrcidValidator());
  }
}
