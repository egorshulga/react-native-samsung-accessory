package com.egorshulga.reactnative.samsungaccessory.messages;

import com.egorshulga.reactnative.samsungaccessory.base.Service;
import com.egorshulga.reactnative.samsungaccessory.constants.Event;
import com.egorshulga.reactnative.samsungaccessory.utils.Result;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.samsung.android.sdk.accessory.SAAgentV2;
import com.samsung.android.sdk.accessory.SAMessage;
import com.samsung.android.sdk.accessory.SAPeerAgent;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SAMessageService extends Service {

  private SAMessage saMessage;
  private Map<Integer, Promise> messagesUnderProcessing = new HashMap<>();

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
  public void initialize(Promise promise) {
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
      protected void onReceive(SAPeerAgent peerAgent, byte[] bytes) {
        String message = new String(bytes);
        SAMessageService.this.onReceivedMessage(peerAgent, message);
      }

      @Override
      protected void onSent(SAPeerAgent saPeerAgent, int id) {
        Promise promise = messagesUnderProcessing.remove(id);
        promise.resolve(null);
      }

      @Override
      protected void onError(SAPeerAgent saPeerAgent, int id, int errorCode) {
        Promise promise = messagesUnderProcessing.remove(id);
        String code;
        if (errorCode == SAMessage.ERROR_PEER_AGENT_UNREACHABLE)
          code = "ERROR_PEER_AGENT_UNREACHABLE";
        else if (errorCode == SAMessage.ERROR_PEER_AGENT_NO_RESPONSE)
          code = "ERROR_PEER_AGENT_NO_RESPONSE";
        else if (errorCode == SAMessage.ERROR_PEER_AGENT_NOT_SUPPORTED)
          code = "ERROR_PEER_AGENT_NOT_SUPPORTED";
        else if (errorCode == SAMessage.ERROR_PEER_SERVICE_NOT_SUPPORTED)
          code = "ERROR_PEER_SERVICE_NOT_SUPPORTED";
        else if (errorCode == SAMessage.ERROR_SERVICE_NOT_SUPPORTED)
          code = "ERROR_SERVICE_NOT_SUPPORTED";
        else
          code = "ERROR_UNKNOWN";
        promise.reject(code, "Unable to send the message");
      }
    };
  }

  @ReactMethod
  public void getServiceChannelSize(Promise promise) {
    super.getServiceChannelSize(promise);
  }

  @ReactMethod
  public void getServiceChannelId(int index, Promise promise) {
    super.getServiceChannelId(index, promise);
  }

  @ReactMethod
  public void getServiceProfileId(Promise promise) {
    super.getServiceProfileId(promise);
  }

  @ReactMethod
  public void getServiceProfileName(Promise promise) {
    super.getServiceProfileName(promise);
  }

  @ReactMethod
  public void findPeers() {
    super.findPeers();
  }

  @ReactMethod
  public void sendMessage(String peerId, String message, Promise promise) {
    SAPeerAgent peer = this.peers.get(peerId);
    if (peer == null) {
      promise.reject("UNKNOWN_PEER", String.format("Peer with id %s is not a known peer", peerId));
      return;
    }
    int messageId;
    try {
      messageId = this.saMessage.send(peer, message.getBytes());
    } catch (IOException e) {
      promise.reject("IO_ERROR", e);
      return;
    }
    messagesUnderProcessing.put(messageId, promise);
  }

  public void onReceivedMessage(SAPeerAgent peer, String message) {
    emitEvent(Event.RECEIVED_MESSAGE, Result.packMessage(peer, message));
  }
}
