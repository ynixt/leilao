export interface AuctionListDto {
  id: number;
  name: number;
  initialValue: number;
  used: boolean;
  userResponsible: { id: number, login: string };
  finished: boolean;
}
