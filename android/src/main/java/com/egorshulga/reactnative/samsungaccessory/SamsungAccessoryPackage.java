package com.egorshulga.reactnative.samsungaccessory;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import org.jetbrains.annotations.NotNull;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SamsungAccessoryPackage implements ReactPackage {
  @NotNull
  public List<NativeModule> createNativeModules(@NotNull ReactApplicationContext reactContext) {
    List<NativeModule> modules = new ArrayList<>();
    modules.add(new SAModule(reactContext));
    modules.add(new SsdkModule(reactContext));
    return modules;
  }

  @NotNull
  public List<ViewManager> createViewManagers(@NotNull ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }
}
