package com.egorshulga.reactnative.samsungaccessory.messages;

import android.content.Context;
import com.egorshulga.reactnative.samsungaccessory.base.Agent;

public class MessageAgent extends Agent<SAMessageService> {

  public MessageAgent(Context context) {
    super("MessageService", context);
  }

}
