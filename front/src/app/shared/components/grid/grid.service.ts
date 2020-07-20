import { Injectable } from '@angular/core';

import { PageableDto } from '../../dto/pageable.dto';
import { PageDto } from '../../dto/page.dto';

import { ClrDatagridStateInterface } from '@clr/angular';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GridService {

  refresh<T>(state: ClrDatagridStateInterface, getItems: (pageeable: PageableDto) => Observable<PageDto<T>>): Promise<PageDto<T>> {
    const sort = state.sort ? `${state.sort.by},${state.sort.reverse ? 'desc' : 'asc'}` : null;

    const page: PageableDto = {
      page: state.page.current - 1,
      size: state.page.size,
    };

    if (sort) {
      page.sort = sort;
    }

    return getItems(page).toPromise();
  }
}
