package com.egorshulga.reactnative.samsungaccessory.messages;

import com.egorshulga.reactnative.samsungaccessory.base.Service;
import com.egorshulga.reactnative.samsungaccessory.constants.Event;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.samsung.android.sdk.accessory.SAAgentV2;
import com.samsung.android.sdk.accessory.SAMessage;
import com.samsung.android.sdk.accessory.SAPeerAgent;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SAMessageService extends Service {

  private SAMessage saMessage;

  @NotNull
  @Override
  public String getName() {
    return "SAMessage";
  }

  public SAMessageService(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @ReactMethod
  public void init(Promise promise) {
    this.initAgent(MessageAgent.class, () -> {
      this.initSAMessage();
      promise.resolve(null);
    }, (Integer errorCode, String message) -> {
      String code;
      if (errorCode == SAAgentV2.ERROR_CLASS_NOT_FOUND) {
        code = "ERROR_CLASS_NOT_FOUND";
      } else if (errorCode == SAAgentV2.ERROR_CONSTRUCTOR_NOT_FOUND) {
        code = "ERROR_CONSTRUCTOR_NOT_FOUND";
      } else if (errorCode == SAAgentV2.ERROR_CONSTRUCTOR_EXCEPTION) {
        code = "ERROR_CONSTRUCTOR_EXCEPTION";
      } else if (errorCode == SAAgentV2.ERROR_AGENT_REQUEST_IN_PROGRESS) {
        code = "ERROR_AGENT_REQUEST_IN_PROGRESS";
      } else {
        code = "UNKNOWN";
      }
      promise.reject(code, message);
    });
  }

  private void initSAMessage() {
    saMessage = new SAMessage(SAMessageService.this.agent) {
      @Override
      protected void onReceive(SAPeerAgent saPeerAgent, byte[] bytes) {
        String message = new String(bytes);
        SAMessageService.this.onReceivedMessage(message);
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
