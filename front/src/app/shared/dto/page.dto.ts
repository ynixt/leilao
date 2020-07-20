export interface PageDto<item> {
  content: item[];
  empty: boolean;
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  pageable: {
    sort: Sort;
    offset: number;
    pageSize: number;
    ageNumber: 0;
  };
  size: number;
  sort: Sort;
  totalElements: number;
  totalPages: number;
}

export interface Sort {
  orted: boolean;
  unsorted: boolean;
  empty: boolean;
}