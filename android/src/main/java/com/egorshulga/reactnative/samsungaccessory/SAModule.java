package com.egorshulga.reactnative.samsungaccessory;

import androidx.annotation.NonNull;

import com.egorshulga.reactnative.samsungaccessory.utils.Result;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.accessory.SA;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class SAModule extends ReactContextBaseJavaModule {
  private SA sa;

  @NotNull
  @Override
  public String getName() {
    return "SA";
  }

  public SAModule(@NonNull ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @ReactMethod
  public final void initialize(Promise promise) {
    sa = new SA();
    try {
      sa.initialize(this.getReactApplicationContext().getApplicationContext());
      promise.resolve(null);
    } catch (SsdkUnsupportedException e) {
      if (e.getType() == SsdkUnsupportedException.LIBRARY_NOT_INSTALLED) {
        promise.reject("LIBRARY_NOT_INSTALLED", e.getMessage(), e);
      } else {
        promise.reject("UNKNOWN", e.getMessage(), e);
      }
    } catch (Exception e) {
      promise.reject(e);
    }
  }

  @ReactMethod
  public final void getVersionCode(Promise promise) {
    if (validateServiceAccessory(promise)) {
      promise.resolve(sa.getVersionCode());
    }
  }

  @ReactMethod
  public final void getVersionName(Promise promise) {
    if (validateServiceAccessory(promise)) {
      promise.resolve(sa.getVersionName());
    }
  }

  @ReactMethod
  public final void isFeatureEnabled(int featureCode, Promise promise) {
    if (validateServiceAccessory(promise)) {
      promise.resolve(sa.isFeatureEnabled(featureCode));
    }
  }

  private boolean validateServiceAccessory(Promise promise) {
    if (sa == null) {
      promise.reject("SA_NULL", "Service accessory should be initialized prior to running this method");
      return false;
    }
    return true;
  }
}
