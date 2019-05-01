import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {HttpResponse} from '../model/HttpResponse';
import {Employee} from '../model/Employee';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  URL = 'http://localhost:8080/employees';

  constructor(private http: HttpClient) {
  }

  public getAllEmployees(page: number, size: number, sortDirection: string, sortTerm: string, searchTerm = '') {
    return this.http.get<HttpResponse>(this.URL + '?page=' + page + '&&size=' + size +
      '&&direction=' + sortDirection + '&&term=' + sortTerm + '&&search=' + searchTerm);
  }

  public updateEmployee(e: Employee) {
    return this.http.put(this.URL, e);
  }

}


