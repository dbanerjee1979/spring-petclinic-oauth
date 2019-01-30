import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Pet } from './pet';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

import { PetType } from './pet-type';

@Injectable({
  providedIn: 'root'
})
export class PetService {
  constructor(private httpClient: HttpClient) { }

  findPetTypes(): Observable<PetType[]> {
    return this.httpClient.get<PetType[]>(`${environment.petApi}/types`, {
      withCredentials: true
    }).pipe(
      map((petTypes) => petTypes.map((petType) => new PetType(petType)))
    );
  }

  create(ownerId: number, pet: Pet): Observable<Pet> {
    return this.httpClient.post<any>(`${environment.ownerApi}/${ownerId}/pets`, pet, {
      withCredentials: true
    });
  }

  update(ownerId: number, id: number, pet: Pet): Observable<Pet> {
    return this.httpClient.put<any>(`${environment.ownerApi}/${ownerId}/pets/${id}`, pet, {
      withCredentials: true
    });
  }
}
