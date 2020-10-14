import type { Transport } from './Constants'

export interface PeerAgent {
  readonly appName: string
  readonly maxAllowedDataSize: number
  readonly maxAllowedMessageSize: number
  readonly peerId: string
  readonly accessory: PeerAccessory
}

export interface PeerAccessory {
  readonly accessoryId: string
  readonly address: string
  readonly name: string
  readonly productId: string
  readonly transportType: Transport
  readonly vendorId: string
}
