import { NativeModules } from 'react-native'
import type { Feature } from './Constants'

export const SA: SamsungAccessory = NativeModules.SA

export interface SamsungAccessory {
  initialize(): Promise<void>
  getVersionCode(): Promise<number>
  getVersionName(): Promise<string>
  isFeatureEnabled(feature: Feature): Promise<boolean>
}
