package com.egorshulga.reactnative.samsungaccessory.base;

import com.egorshulga.reactnative.samsungaccessory.constants.Event;
import com.egorshulga.reactnative.samsungaccessory.utils.BiSupplier;
import com.egorshulga.reactnative.samsungaccessory.utils.Result;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.samsung.android.sdk.accessory.SAAgentV2;
import com.samsung.android.sdk.accessory.SAPeerAgent;

import java.util.HashMap;
import java.util.Map;

public abstract class Service extends ReactContextBaseJavaModule {
  protected Agent<? extends Service> agent;
  protected ReactApplicationContext reactContext;
  private Map<String, SAPeerAgent> foundPeers = new HashMap<>();
  private Map<String, SAPeerAgent> availablePeers = new HashMap<>();

  public Service(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  public abstract void init(Promise promise);

    protected void initAgent(Class<? extends Agent<? extends Service>> clazz,
                           Runnable success, BiSupplier<Integer, String> error) {
    SAAgentV2.requestAgent(reactContext.getApplicationContext(), clazz.getName(),
      new SAAgentV2.RequestAgentCallback() {
        @Override
        public void onAgentAvailable(SAAgentV2 agent) {
          Service.this.agent = clazz.cast(agent);
          if (success != null) {
            success.run();
          }
        }
        @Override
        public void onError(int errorCode, String message) {
          if (error != null) {
            error.apply(errorCode, message);
          }
        }
      });
  }

  @ReactMethod
  public void findPeers() {
    this.agent.findPeers();
  }

  protected void onFoundPeers(SAPeerAgent[] peerAgents, int status) {
    WritableMap result = Arguments.createMap();
    result.putInt("status", status);
    if (status == SAAgentV2.PEER_AGENT_FOUND) {
      WritableArray peers = Arguments.createArray();
      for (SAPeerAgent peer : peerAgents) {
        peers.pushMap(Result.pack(peer));
      }
      result.putArray("peers", peers);
    }
    emitEvent(Event.PEERS_FOUND, result);
  }

  protected void onUpdatedPeers(SAPeerAgent[] peerAgents, int status) {
    WritableMap result = Arguments.createMap();
    result.putInt("status", status);
    WritableArray peers = Arguments.createArray();
    for (SAPeerAgent peer : peerAgents) {
      peers.pushMap(Result.pack(peer));
    }
    result.putArray("peers", peers);
    emitEvent(Event.PEERS_UPDATED, result);
  }

  protected void emitEvent(String name, Object data) {
    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(name, data);
  }
}
