import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Vet } from './vet';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VetsService {
  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<Vet[]> {
    return this.httpClient.get<Vet[]>(environment.vetsApi, {
      withCredentials: true
    }).pipe(
      map((vets) => vets.map((data: any) => new Vet(data)))
    );
  }
}
