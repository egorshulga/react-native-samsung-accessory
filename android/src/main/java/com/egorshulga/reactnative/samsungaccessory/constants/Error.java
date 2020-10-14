package com.egorshulga.reactnative.samsungaccessory.constants;

import com.samsung.android.sdk.SsdkUnsupportedException;

import java.util.HashMap;
import java.util.Map;

public class Error {
  public static final int VENDOR_NOT_SUPPORTED = SsdkUnsupportedException.VENDOR_NOT_SUPPORTED;
  public static final int DEVICE_NOT_SUPPORTED = SsdkUnsupportedException.DEVICE_NOT_SUPPORTED;
  public static final int LIBRARY_NOT_INSTALLED = SsdkUnsupportedException.LIBRARY_NOT_INSTALLED;
  public static final int LIBRARY_UPDATE_IS_REQUIRED = SsdkUnsupportedException.LIBRARY_UPDATE_IS_REQUIRED;
  public static final int LIBRARY_UPDATE_IS_RECOMMENDED = SsdkUnsupportedException.LIBRARY_UPDATE_IS_RECOMMENDED;

  public static final Map<Integer, String> codes = new HashMap<Integer, String>();

  static {
    codes.put(VENDOR_NOT_SUPPORTED, "VENDOR_NOT_SUPPORTED");
    codes.put(DEVICE_NOT_SUPPORTED, "DEVICE_NOT_SUPPORTED");
    codes.put(LIBRARY_NOT_INSTALLED, "LIBRARY_NOT_INSTALLED");
    codes.put(LIBRARY_UPDATE_IS_REQUIRED, "LIBRARY_UPDATE_IS_REQUIRED");
    codes.put(LIBRARY_UPDATE_IS_RECOMMENDED, "LIBRARY_UPDATE_IS_RECOMMENDED");
  }
}
