package com.egorshulga.reactnative.samsungaccessory.base;

import com.egorshulga.reactnative.samsungaccessory.constants.Event;
import com.egorshulga.reactnative.samsungaccessory.utils.Result;
import com.facebook.react.bridge.Arguments;
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

public abstract class AgentModule extends ReactContextBaseJavaModule {
  protected Agent<? extends AgentModule> agent;
  protected ReactApplicationContext reactContext;
  private Map<String, SAPeerAgent> foundPeers = new HashMap<>();
  private Map<String, SAPeerAgent> availablePeers = new HashMap<>();

  public AgentModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  protected void setAgent(Agent<? extends AgentModule> agent) {
    this.agent = agent;
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
