import { NativeEventEmitter, NativeModules } from 'react-native'
import type { SamsungAccessoryAgent } from './Agent'

export const SAMessage: SamsungAccessoryMessage = NativeModules.SAMessage

export const SAMessageEvents = new NativeEventEmitter(NativeModules.SAMessage)

export interface SamsungAccessoryMessage extends SamsungAccessoryAgent {
  sendMessage(message: string): Promise<void>;
}
