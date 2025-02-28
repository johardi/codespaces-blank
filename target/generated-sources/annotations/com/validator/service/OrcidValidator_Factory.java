package com.validator.service;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class OrcidValidator_Factory implements Factory<OrcidValidator> {
  @Override
  public OrcidValidator get() {
    return newInstance();
  }

  public static OrcidValidator_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OrcidValidator newInstance() {
    return new OrcidValidator();
  }

  private static final class InstanceHolder {
    private static final OrcidValidator_Factory INSTANCE = new OrcidValidator_Factory();
  }
}
