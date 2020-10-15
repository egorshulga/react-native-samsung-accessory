export interface Service {
  findPeers(): void
  getServiceChannelSize(): Promise<number>
  getServiceChannelId(index: number): Promise<number>
  getServiceProfileId(): Promise<string>
  getServiceProfileName(): Promise<string>
}
