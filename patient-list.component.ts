import { Component, OnInit } from '@angular/core';
import { Patient } from '../model/patient.model';
import { PatientService } from '../services/patient.service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {

  patients : Patient[] = [];

  constructor(private patientService : PatientService) { }

  ngOnInit(): void {
    this.getPatients();
  }

  public getPatients() : void{
    this.patientService.getPatients().subscribe(data=>{
      this.patients = data;
    })
  }

  public deletePatient(id : number) : void{
    this.patientService.deletePatient(id).subscribe(data=>{
      this.getPatients();
    })
  }

}
