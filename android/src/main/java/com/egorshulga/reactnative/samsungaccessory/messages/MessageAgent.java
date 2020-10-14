package com.egorshulga.reactnative.samsungaccessory.messages;

import android.content.Context;
import com.egorshulga.reactnative.samsungaccessory.base.Agent;

public class MessageAgent extends Agent<SAMessageModule> {

  public MessageAgent(SAMessageModule messageModule, Context context) {
    super(messageModule, "MessageService", context);
  }

  public void sendMessage(String message) {
  }
}
