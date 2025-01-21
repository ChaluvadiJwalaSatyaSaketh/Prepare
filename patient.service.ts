import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient } from '../model/patient.model';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  backendUrl : string = "https://ide-ffecbdcfbbfccf322525212aaeddbcbfbfone.premiumproject.examly.io/proxy/3001/patients";

  constructor(private httpClient : HttpClient) { }

  public getPatients() : Observable<Patient[]>{
    return this.httpClient.get(this.backendUrl) as Observable<Patient[]>;
  }

  public addPatient(patient : Patient) : Observable<Patient> {
    return this.httpClient.post(this.backendUrl,patient) as Observable<Patient>;
  }

  public deletePatient(id : number) : Observable<any>{
    return this.httpClient.delete(this.backendUrl+"/"+id);
  }
}
