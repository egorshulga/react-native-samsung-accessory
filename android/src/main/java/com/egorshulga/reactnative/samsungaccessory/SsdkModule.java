package com.egorshulga.reactnative.samsungaccessory;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.accessory.SA;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SsdkModule extends ReactContextBaseJavaModule {
  @NotNull
  @Override
  public String getName() {
    return "Ssdk";
  }

  public SsdkModule(@NonNull ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put("DEVICE_NOT_SUPPORTED", SsdkUnsupportedException.DEVICE_NOT_SUPPORTED);
    constants.put("LIBRARY_NOT_INSTALLED", SsdkUnsupportedException.LIBRARY_NOT_INSTALLED);
    constants.put("LIBRARY_UPDATE_IS_RECOMMENDED", SsdkUnsupportedException.LIBRARY_UPDATE_IS_RECOMMENDED);
    constants.put("LIBRARY_UPDATE_IS_REQUIRED", SsdkUnsupportedException.LIBRARY_UPDATE_IS_REQUIRED);
    constants.put("VENDOR_NOT_SUPPORTED", SsdkUnsupportedException.VENDOR_NOT_SUPPORTED);
    return constants;
  }
}
