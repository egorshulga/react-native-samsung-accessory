import { EmitterSubscription, NativeEventEmitter, NativeModules } from 'react-native'
import type { SamsungAccessoryAgent } from './Agent'
import type { PeerAvailabilityStatus, PeerSearchStatus } from './Constants'
import type { PeerAgent } from './PeerAgent'

export const SAMessage: SamsungAccessoryMessage = NativeModules.SAMessage

const emitter = new NativeEventEmitter(NativeModules.SAMessage)

export interface SamsungAccessoryMessage extends SamsungAccessoryAgent {
  sendMessage(message: string): Promise<void>
}

export class MessageEventEmitter {
  addPeersFoundListener(listener: (response: PeersFoundResponse) => void, context?: any): EmitterSubscription {
    return emitter.addListener(MessageEvent.PEERS_FOUND, listener, context)
  }

  addPeersUpdatedListener(listener: (response: PeersUpdatedResponse) => void, context?: any): EmitterSubscription {
    return emitter.addListener(MessageEvent.PEERS_UPDATED, listener, context)
  }

  removeSubscription(subscription: EmitterSubscription): void {
    emitter.removeSubscription(subscription)
  }
}

export const SAMessageEventEmitter = new MessageEventEmitter()

export enum MessageEvent {
  PEERS_FOUND = 'PEERS_FOUND',
  PEERS_UPDATED = 'PEERS_UPDATED',
  RECEIVED_MESSAGE = 'RECEIVED_MESSAGE',
}

export interface PeersFoundResponse {
  readonly status: PeerSearchStatus
  readonly peers: PeerAgent[]
}

export interface PeersUpdatedResponse {
  readonly status: PeerAvailabilityStatus
  readonly peers: PeerAgent[]
}
