import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from 'src/environments/environment';
import { Owner } from './owner';
import { Pet } from './pet';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {
  constructor(private httpClient: HttpClient) { }

  findOwners(lastName: string): Observable<Owner[]> {
    return this.httpClient.get<Owner[]>(environment.ownerApi, {
      params: new HttpParams().set('lastName', lastName || '')
    }).pipe(
      map((owners: Owner[]) => owners.map((owner) => new Owner(Object.assign(owner))))
    );
  }

  findDetailsById(id: number): Observable<Owner> {
    return this.httpClient.get<Owner>(`${environment.ownerApi}/${id}/details`).pipe(
      map((owner: Owner) => new Owner(Object.assign(owner)))
    );
  }

  create(owner: Owner): Observable<Owner> {
    return this.httpClient.post<Owner>(environment.ownerApi, owner);
  }

  update(id: number, owner: Owner): Observable<Owner> {
    return this.httpClient.put<Owner>(`${environment.ownerApi}/${id}`, owner);
  }
}
