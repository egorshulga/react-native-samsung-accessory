import { NativeModules } from 'react-native'
import type { SamsungAccessoryAgent } from './Agent'

export const SAMessage: SamsungAccessoryMessage = NativeModules.SAMessage

export interface SamsungAccessoryMessage extends SamsungAccessoryAgent {
  sendMessage(message: string): Promise<void>;
}
