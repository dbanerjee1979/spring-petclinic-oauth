import { Component, OnInit } from '@angular/core';
import { OwnerService } from '../owner.service';
import { RouterModule, Routes, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Owner } from '../owner';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OwnerEditComponent } from '../owner-edit/owner-edit.component';
import { PetEditComponent } from '../pet-edit/pet-edit.component';
import { PetService } from '../pet.service';
import { Pet } from '../pet';
import { VisitEditComponent } from '../visit-edit/visit-edit.component';
import { VisitService } from '../visit.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-owner-detail',
  templateUrl: './owner-detail.component.html',
  styleUrls: ['./owner-detail.component.scss']
})
export class OwnerDetailComponent implements OnInit {
  id: number;
  owner: Owner | {} = {};
  editOwnerAllowed: boolean = false;
  newPetAllowed: boolean = false;
  editPetAllowed: boolean = false;
  newVisitAllowed: boolean = false;

  constructor(
    private modalService: NgbModal,
    private ownerService: OwnerService,
    private petService: PetService,
    private visitService: VisitService,
    private auth: AuthService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.pipe(
      switchMap((params: ParamMap) => this.ownerService.findDetailsById(this.id = +params.get('id'))),
      switchMap((owner) => { this.owner = owner; return this.auth.authenticated })
    ).subscribe(() => {
      this.editOwnerAllowed = false;
      this.newPetAllowed = false;
      this.editPetAllowed = false;
      this.newVisitAllowed = false;
      if (this.owner instanceof Owner) {
        this.editOwnerAllowed = this.auth.isOwner(this.owner.username);
        this.newPetAllowed = this.auth.hasAdmin();
        this.editPetAllowed = this.auth.isOwner(this.owner.username);
        this.newVisitAllowed = this.auth.isOwner(this.owner.username);
      }
    });
  }

  editOwner() {
    const editorRef = this.modalService.open(OwnerEditComponent);
    editorRef.componentInstance.owner = Object.assign({}, this.owner);
    editorRef.result.then(
      (owner) => this.ownerService.update(this.id, owner).pipe(
        switchMap(() => this.ownerService.findDetailsById(this.id))
      ).subscribe((owner) => this.owner = owner),
      (reason) => console.log(reason)
    );
  }

  newPet() {
    const editorRef = this.modalService.open(PetEditComponent);
    editorRef.componentInstance.pet = {};
    editorRef.result.then(
      (pet) => this.petService.create(this.id, pet).pipe(
        switchMap(() => this.ownerService.findDetailsById(this.id))
      ).subscribe((owner) => this.owner = owner),
      (reason) => console.log(reason)
    );
  }

  editPet(pet: Pet) {
    const editorRef = this.modalService.open(PetEditComponent);
    editorRef.componentInstance.pet = Object.assign({}, pet);
    editorRef.result.then(
      (pet) => this.petService.update(this.id, pet.id, pet).pipe(
        switchMap(() => this.ownerService.findDetailsById(this.id))
      ).subscribe((owner) => this.owner = owner),
      (reason) => console.log(reason)
    );
  }

  createVisit(pet: Pet) {
    const editorRef = this.modalService.open(VisitEditComponent);
    editorRef.componentInstance.visit = {};
    editorRef.result.then(
      (visit) => this.visitService.create(this.id, pet.id, visit).pipe(
        switchMap(() => this.ownerService.findDetailsById(this.id))
      ).subscribe((owner) => this.owner = owner),
      (reason) => console.log(reason)
    );
  }
}
