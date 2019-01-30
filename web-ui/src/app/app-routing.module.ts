import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { OwnersComponent } from './owners/owners.component';
import { VeterinariansComponent } from './veterinarians/veterinarians.component';
import { OwnerDetailComponent } from './owner-detail/owner-detail.component';

const routes: Routes = [
  { path: 'welcome', component: WelcomeComponent },
  { path: 'owners',  component: OwnersComponent },
  { path: 'owners/:id',  component: OwnerDetailComponent },
  { path: 'vets',    component: VeterinariansComponent },
  { path: '**',      redirectTo: '/welcome' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
