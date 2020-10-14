package com.egorshulga.reactnative.samsungaccessory.messages;

import com.egorshulga.reactnative.samsungaccessory.base.AgentModule;
import com.egorshulga.reactnative.samsungaccessory.constants.Event;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.samsung.android.sdk.accessory.SAMessage;
import com.samsung.android.sdk.accessory.SAPeerAgent;

import org.jetbrains.annotations.NotNull;

public class SAMessageModule extends AgentModule {

  private SAMessage saMessage;

  @NotNull
  @Override
  public String getName() {
    return "SAMessage";
  }

  public SAMessageModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.setAgent(new MessageAgent(this, reactContext.getApplicationContext()));

    saMessage = new SAMessage(agent) {
      @Override
      protected void onReceive(SAPeerAgent saPeerAgent, byte[] bytes) {
        String message = new String(bytes);
        SAMessageModule.this.onReceivedMessage(message);
      }

      @Override
      protected void onSent(SAPeerAgent saPeerAgent, int id) {
      }

      @Override
      protected void onError(SAPeerAgent saPeerAgent, int id, int errorCode) {
      }
    };
  }

  @ReactMethod
  public void sendMessage(String message, Promise promise) {
  }

  public void onReceivedMessage(String message) {
    emitEvent(Event.RECEIVED_MESSAGE, message);
  }
}
