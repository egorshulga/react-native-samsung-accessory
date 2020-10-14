package com.egorshulga.reactnative.samsungaccessory.utils;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.samsung.android.sdk.accessory.SAPeerAccessory;
import com.samsung.android.sdk.accessory.SAPeerAgent;

public class Result {

  public static WritableMap pack(SAPeerAgent peer) {
    WritableMap result = Arguments.createMap();
    result.putString("appName", peer.getAppName());
    result.putInt("maxAllowedDataSize", peer.getMaxAllowedDataSize());
    result.putInt("maxAllowedMessageSize", peer.getMaxAllowedMessageSize());
    result.putString("peerId", peer.getPeerId());
    result.putString("profileVersion", peer.getProfileVersion());
    result.putMap("accessory", pack(peer.getAccessory()));
    return result;
  }

  public static WritableMap pack(SAPeerAccessory peerAccessory) {
    WritableMap result = Arguments.createMap();
    result.putString("accessoryId", peerAccessory.getAccessoryId());
    result.putString("address", peerAccessory.getAddress());
    result.putString("name", peerAccessory.getName());
    result.putString("productId", peerAccessory.getProductId());
    result.putInt("transportType", peerAccessory.getTransportType());
    result.putString("vendorId", peerAccessory.getVendorId());
    return result;
  }

}
