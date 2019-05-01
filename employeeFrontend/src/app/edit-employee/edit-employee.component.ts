import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Employee} from '../model/Employee';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpService} from '../services/http.service';
import {EmployeeListService} from '../services/employee-list.service';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})
export class EditEmployeeComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<EditEmployeeComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Employee,
              private formBuilder: FormBuilder,
              private http: HttpService,
              private employeeService: EmployeeListService) {
    this.employeeForm = this.createFormGroup(this.formBuilder);
  }

  employeeForm: FormGroup;

  createFormGroup(formBuilder: FormBuilder) {
    return formBuilder.group({
      empNo: [this.data.empNo, [Validators.required]],
      birthDate: this.data.birthDate,
      firstName: this.data.firstName,
      lastName: this.data.lastName,
      gender: this.data.gender,
      hireDate: this.data.hireDate,
    });
  }

  onNoClick(): void {

    this.dialogRef.close();
  }

  ngOnInit() {
  }


  submit() {
    this.http.updateEmployee(this.employeeForm.value).subscribe(data => {
      console.log(data);
      this.onNoClick();
    });
  }
}
