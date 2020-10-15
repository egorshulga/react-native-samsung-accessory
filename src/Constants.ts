import { NativeModules } from 'react-native'

const Constants = NativeModules.SAConstants

export enum Feature {
  DeviceAccessory = Constants.DEVICE_ACCESSORY,
  ServiceMessage = Constants.SERVICE_MESSAGE,
}

export enum PeerSearchStatus {
  PEER_AGENT_FOUND = Constants.PEER_AGENT_FOUND,
  FINDPEER_DEVICE_NOT_CONNECTED = Constants.FINDPEER_DEVICE_NOT_CONNECTED,
  FINDPEER_SERVICE_NOT_FOUND = Constants.FINDPEER_SERVICE_NOT_FOUND,
  FINDPEER_DUPLICATE_REQUEST = Constants.FINDPEER_DUPLICATE_REQUEST,
}

export enum PeerAvailabilityStatus {
  PEER_AGENT_AVAILABLE = Constants.PEER_AGENT_AVAILABLE,
  PEER_AGENT_UNAVAILABLE = Constants.PEER_AGENT_UNAVAILABLE,
}

export enum Transport {
  TRANSPORT_WIFI = Constants.TRANSPORT_WIFI,
  TRANSPORT_BT = Constants.TRANSPORT_BT,
  TRANSPORT_BLE = Constants.TRANSPORT_BLE,
  TRANSPORT_USB = Constants.TRANSPORT_USB,
  TRANSPORT_MOBILE = Constants.TRANSPORT_MOBILE,
}
