import {Employee} from './Employee';


export class HttpResponse {
  content: Employee[];
  totalElements: number;
  last: boolean;
  totalPages: number;
  size: number;
  numberOfElements: number;
  first: boolean;
  empty: boolean;
  sort: Sort;
  pageable: Pageable;
}

interface Sort {
  sorted: boolean;
  unsorted: boolean;
  empty: boolean;
}

interface Pageable {
  sort: Sort;
  offset: number;
  pageSize: number;
  pageNumber: number;
  unpaged: boolean;
  paged: boolean;
}
