import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule, NgbDateAdapter, NgbDateNativeAdapter } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { OwnersComponent } from './owners/owners.component';
import { VeterinariansComponent } from './veterinarians/veterinarians.component';
import { OwnerDetailComponent } from './owner-detail/owner-detail.component';
import { OwnerEditComponent } from './owner-edit/owner-edit.component';
import { PetEditComponent } from './pet-edit/pet-edit.component';
import { VisitEditComponent } from './visit-edit/visit-edit.component';
import { AuthService } from './auth.service';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    OwnersComponent,
    VeterinariansComponent,
    OwnerDetailComponent,
    OwnerEditComponent,
    PetEditComponent,
    VisitEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  entryComponents: [
    OwnerEditComponent,
    PetEditComponent,
    VisitEditComponent
  ],
  providers: [
    { provide: NgbDateAdapter, useClass: NgbDateNativeAdapter },
    { provide: APP_INITIALIZER, 
      useFactory: (auth: AuthService) => () => auth.check(),
      deps: [ AuthService ],
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
