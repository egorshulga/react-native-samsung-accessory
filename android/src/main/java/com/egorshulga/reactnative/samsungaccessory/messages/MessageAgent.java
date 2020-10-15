package com.egorshulga.reactnative.samsungaccessory.messages;

import android.content.Context;
import com.egorshulga.reactnative.samsungaccessory.base.Agent;

public class MessageAgent extends Agent<SAMessageService> {

  public MessageAgent(SAMessageService messageModule, Context context) {
    super(messageModule, "MessageService", context);
  }

  public void sendMessage(String message) {
  }
}
