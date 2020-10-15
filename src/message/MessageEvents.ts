import { EmitterSubscription, NativeEventEmitter, NativeModules } from 'react-native'
import type { PeersFoundResponse, PeersUpdatedResponse } from './Message'

const emitter = new NativeEventEmitter(NativeModules.SAMessage)

export class MessageEvents {
  addPeersFoundListener(listener: (response: PeersFoundResponse) => void, context?: any): EmitterSubscription {
    return emitter.addListener(MessageEvent.PEERS_FOUND, listener, context)
  }

  addPeersUpdatedListener(listener: (response: PeersUpdatedResponse) => void, context?: any): EmitterSubscription {
    return emitter.addListener(MessageEvent.PEERS_UPDATED, listener, context)
  }

  addMessageListener(listener: (message: string) => void, context?: any): EmitterSubscription {
    return emitter.addListener(MessageEvent.RECEIVED_MESSAGE, listener, context)
  }

  removeSubscription(subscription: EmitterSubscription): void {
    emitter.removeSubscription(subscription)
  }
}

export const SAMessageEvents = new MessageEvents()

export enum MessageEvent {
  PEERS_FOUND = 'PEERS_FOUND',
  PEERS_UPDATED = 'PEERS_UPDATED',
  RECEIVED_MESSAGE = 'RECEIVED_MESSAGE',
}
