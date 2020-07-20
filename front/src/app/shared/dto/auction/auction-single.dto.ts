export interface AuctionSingleDto {
  name: string;
  initialValue: number;
  used: boolean;
  openDate: string;
  endDate?: string;
  userResponsible?: { id: number, login: string };
}
