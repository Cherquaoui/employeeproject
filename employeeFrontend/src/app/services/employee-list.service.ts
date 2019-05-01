import {Injectable} from '@angular/core';
import {HttpService} from './http.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpResponse} from '../model/HttpResponse';
import {map, share, switchMap} from 'rxjs/operators';
import {CollectionViewer, DataSource} from '@angular/cdk/collections';

@Injectable({
  providedIn: 'root'
})
export class EmployeeListService implements DataSource<HttpResponse> {
  constructor(private httpService: HttpService) {
  }

  private employeeSubject = new BehaviorSubject<HttpResponse>(null);
  public employeeObservable = this.employeeSubject.asObservable().pipe(share());

  connect(collectionViewer: CollectionViewer): Observable<any> {
    return this.employeeObservable.pipe(map(data => {
        if (data !== null) {
          return data.content;
        }
      }
    ));
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.employeeSubject.complete();
  }

  searchEmployee(data: Observable<any>, page: number, size: number, sortDirection, sortTerm) {

    data.pipe(switchMap(st => this.httpService.getAllEmployees(page, size, sortDirection, sortTerm, st))).subscribe(
      data2 => this.employeeSubject.next(data2)
    )
    ;
  }


  loadEmployees(page: number, size: number, sortDirection, sortTerm, search = '') {
    this.httpService.getAllEmployees(page, size, sortDirection, sortTerm, search).subscribe(data => {
        this.employeeSubject.next(data);
      }
    )
    ;

  }
}
