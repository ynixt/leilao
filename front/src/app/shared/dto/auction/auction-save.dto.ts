export interface AuctionSaveDto {
  name: string;
  initialValue: number;
  used: boolean;
  openDate: string;
  endDate?: string;
}
