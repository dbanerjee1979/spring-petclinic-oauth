import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map, catchError } from 'rxjs/operators';
import { of, Observable } from 'rxjs';

type AuthData = { username: string, authorities: string[] }

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  auth: AuthData;
  public authenticated: Observable<boolean>;

  constructor(private httpClient: HttpClient) { }

  check() {
    this.authenticated = this.httpClient.get<AuthData>(environment.meApi, { 
      withCredentials: true 
    }).pipe(
      map((auth) => { 
        this.auth = auth; 
        return true; 
      }),
      catchError((error: HttpErrorResponse) => {
        this.auth = undefined;
        return of(false);
      })
    );
  }

  hasAuth(neededRole: string) {
    return this.auth.authorities.filter((role) => role === neededRole).length !== 0
  }

  hasAdmin() {
    return this.auth && this.hasAuth('ROLE_ADMIN');
  }

  isOwner(neededOwner: string) {
    return this.auth && this.hasAuth('ROLE_OWNER') && this.auth.username === neededOwner;
  }
}
