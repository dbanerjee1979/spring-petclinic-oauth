import { Injectable } from '@angular/core';
import { Visit } from './visit';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VisitService {
  constructor(private httpClient: HttpClient) { }

  create(ownerId: number, petId: number, visit: Visit): Observable<Visit> {
    return this.httpClient.post<any>(`${environment.ownerApi}/${ownerId}/pets/${petId}/visits`, visit);
  }
}
