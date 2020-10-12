import { NativeModules } from 'react-native'

export const { SA } = NativeModules as {
  SA: SamsungAccessory
}

export interface SamsungAccessory {
  initialize(): Promise<void>
  getVersionCode(): Promise<number>
  getVersionName(): Promise<string>
  isFeatureEnabled(feature: AccessoryFeature): Promise<boolean>
}

export enum AccessoryFeature {
  DeviceAccessory = NativeModules.SA.DEVICE_ACCESSORY,
  SERVICE_MESSAGE = NativeModules.SA.SERVICE_MESSAGE,
}
