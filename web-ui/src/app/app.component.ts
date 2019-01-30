import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  authenticated: boolean = false;


  constructor (private authService: AuthService) { }

  ngOnInit() {
    this.authService.authenticated.subscribe((authenticated) => this.authenticated = authenticated);
  }
}
