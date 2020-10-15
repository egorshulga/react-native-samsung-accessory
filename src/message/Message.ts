import { NativeModules } from 'react-native'
import type { Service } from '../base/Service'
import type { PeerAvailabilityStatus, PeerSearchStatus } from '../Constants'
import type { PeerAgent } from '../models/PeerAgent'

export const SAMessage: SAMessageService = NativeModules.SAMessage

export interface SAMessageService extends Service {
  initialize(): Promise<void>
  sendMessage(peerId: string, message: string): Promise<void>
}

export enum SAMessageInitError {
  ERROR_CLASS_NOT_FOUND = 'ERROR_CLASS_NOT_FOUND',
  ERROR_CONSTRUCTOR_NOT_FOUND = 'ERROR_CONSTRUCTOR_NOT_FOUND',
  ERROR_CONSTRUCTOR_EXCEPTION = 'ERROR_CONSTRUCTOR_EXCEPTION',
  ERROR_AGENT_REQUEST_IN_PROGRESS = 'ERROR_AGENT_REQUEST_IN_PROGRESS',
}

export interface PeersFoundResponse {
  readonly status: PeerSearchStatus
  readonly peers: PeerAgent[]
}

export interface PeersUpdatedResponse {
  readonly status: PeerAvailabilityStatus
  readonly peers: PeerAgent[]
}

export interface ReceivedMessageResponse {
  readonly peerId: string
  readonly message: string
}
