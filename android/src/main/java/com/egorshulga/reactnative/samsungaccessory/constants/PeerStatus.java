package com.egorshulga.reactnative.samsungaccessory.constants;

import com.samsung.android.sdk.accessory.SAAgentV2;

public class PeerStatus {
  public class Search {
    public static final int PEER_AGENT_FOUND = SAAgentV2.PEER_AGENT_FOUND;
    public static final int FINDPEER_DEVICE_NOT_CONNECTED = SAAgentV2.FINDPEER_DEVICE_NOT_CONNECTED;
    public static final int FINDPEER_SERVICE_NOT_FOUND = SAAgentV2.FINDPEER_SERVICE_NOT_FOUND;
    public static final int FINDPEER_DUPLICATE_REQUEST = SAAgentV2.FINDPEER_DUPLICATE_REQUEST;
  }

  public class Availability {
    public static final int PEER_AGENT_AVAILABLE = SAAgentV2.PEER_AGENT_AVAILABLE;
    public static final int PEER_AGENT_UNAVAILABLE = SAAgentV2.PEER_AGENT_UNAVAILABLE;
  }
}
