import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {HttpService} from '../services/http.service';
import {MatDialog, MatPaginator, MatSort} from '@angular/material';
import {tap} from 'rxjs/internal/operators/tap';
import {merge} from 'rxjs';
import {EmployeeListService} from '../services/employee-list.service';
import {EditEmployeeComponent} from '../edit-employee/edit-employee.component';
import {Employee} from '../model/Employee';
import {FormControl} from '@angular/forms';


@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit, AfterViewInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  length;
  colonnes = ['empNo', 'birthDate', 'fullName', 'gender', 'hireDate',
    'salary', 'title', 'edit'];

  search = new FormControl('');

  constructor(private httpService: HttpService,
              private employeeListService: EmployeeListService,
              public dialog: MatDialog) {
  }

  openDialog(e: Employee): void {
    const dialogRef = this.dialog.open(EditEmployeeComponent, {
      width: '250px',
      data: e
    });

    dialogRef.afterClosed().pipe(tap(() => this.ngOnInit())).subscribe();
  }


  ngAfterViewInit() {
    this.employeeListService.employeeObservable.subscribe(data => {
        this.length = data.totalPages;
      }
    );

    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
    merge(this.sort.sortChange, this.paginator.page).subscribe(() => this.loadEmployeePage());
  }

  loadEmployeePage() {
    this.employeeListService.loadEmployees(this.paginator.pageIndex,
      this.paginator.pageSize,
      this.sort.direction,
      this.sort.active);
  }


  search2() {
    this.employeeListService.searchEmployee(this.search.valueChanges,
      this.paginator.pageIndex,
      this.paginator.pageSize,
      this.sort.direction,
      this.sort.active);
  }

  ngOnInit() {
    this.employeeListService.loadEmployees(0, 25, 'ASC', 'empNo');
    this.search.valueChanges.subscribe(() => this.search2());

  }
}



