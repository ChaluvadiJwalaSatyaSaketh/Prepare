import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PatientService } from '../services/patient.service';
import { Patient } from '../model/patient.model';

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css']
})
export class AddPatientComponent implements OnInit {

  patientForm : FormGroup

  constructor(private formBuilder : FormBuilder, private patientService : PatientService) { 

    this.patientForm = formBuilder.group({
      name : formBuilder.control("",Validators.required),
      age : formBuilder.control("",Validators.required),
      gender : formBuilder.control("",Validators.required),
      bloodGroup : formBuilder.control("",Validators.required),
      medicalHistory : formBuilder.control("",Validators.required)
    })
  }

  ngOnInit(): void {
  }

  public addNewPatient() : void{
    if(this.patientForm.valid){
      let patient : Patient = this.patientForm.value;
      this.patientService.addPatient(patient).subscribe(newPatient=>{
        this.patientForm.reset();
      })
    }
  }

}
