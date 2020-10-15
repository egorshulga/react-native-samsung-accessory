package com.egorshulga.reactnative.samsungaccessory.base;

import android.content.Context;
import com.samsung.android.sdk.accessory.SAAgentV2;
import com.samsung.android.sdk.accessory.SAPeerAgent;
import com.samsung.android.sdk.accessory.SASocket;

public abstract class Agent<TModule extends Service> extends SAAgentV2 {
  protected TModule agentModule;

  protected Agent(String name, Context context) {
    super(name, context);
  }

  protected Agent(String name, Context context, Class<? extends SASocket> socketClass) {
    super(name, context, socketClass);
  }

  public void setAgentModule(Service agentModule) {
    this.agentModule = (TModule) agentModule;
  }

  public void findPeers() {
    this.findPeerAgents();
  }

  @Override
  protected void onFindPeerAgentsResponse(SAPeerAgent[] peerAgents, int status) {
    agentModule.onFoundPeers(peerAgents, status);
  }

  @Override
  protected void onPeerAgentsUpdated(SAPeerAgent[] peerAgents, int status) {
    agentModule.onUpdatedPeers(peerAgents, status);
  }
}
