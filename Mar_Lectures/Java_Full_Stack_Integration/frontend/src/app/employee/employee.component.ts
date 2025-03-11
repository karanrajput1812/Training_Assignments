import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { EmployeeService } from '../shared/employee.service';
import { Employee } from '../shared/employee.model';

@Component({
  selector: 'app-employee',
  standalone: false,
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css',
  providers: [EmployeeService],
})
export class EmployeeComponent implements OnInit {
  constructor(public es: EmployeeService) {}

  update: boolean = false;
  ngOnInit(): void {
    this.resetForm();
  }

  onSubmit(form: NgForm) {
    if (!this.update) {
      this.es.postEmployee(form.value).subscribe((res) => {
        this.resetForm(form);
        console.log('Employee added successfully');
      });
    } else {
      this.es.putEmployee(form.value).subscribe((res) => {
        this.resetForm(form);
        console.log('Employee updated successfully');
        this.update = false;
      });
    }
  }

  resetForm(form?: NgForm) {
    if (form) {
      form.reset();
    }
    this.es.selectedEmployee = new Employee();
    this.refreshEmployees();
  }

  refreshEmployees() {
    this.es.getAllEmployees().subscribe((res) => {
      this.es.employees = res as Employee[];
    });
  }

  updateEmp(emp: Employee) {
    this.es.selectedEmployee = emp;
    this.update = true;
  }

  deleteEmp(id: number | undefined) {
    if (id) {
      if (confirm('Do you want to delete?')) {
        this.es.deleteEmployee(id).subscribe((res) => {
          this.resetForm();
        });
      }
    }
  }
}
