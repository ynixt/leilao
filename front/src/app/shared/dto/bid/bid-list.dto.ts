export interface BidListDto {
  id: number;
  value: number;
  date: number;
  madeByUser: { id: number, login: string };
}
