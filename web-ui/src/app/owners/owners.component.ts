import { Component, OnInit } from '@angular/core';
import { OwnerService } from '../owner.service';
import { Owner } from '../owner';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OwnerEditComponent } from '../owner-edit/owner-edit.component';
import { switchMap } from 'rxjs/operators';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-owners',
  templateUrl: './owners.component.html',
  styleUrls: ['./owners.component.scss']
})
export class OwnersComponent implements OnInit {
  owners: Owner[];
  lastName: string;
  newOwnerAllowed: boolean = false;

  constructor(
    private ownerService: OwnerService, 
    private modalService: NgbModal,
    private auth: AuthService) { }

  ngOnInit() {
    this.findOwners();
    this.auth.authenticated.subscribe((authenticated: boolean) => {
      console.log(this.auth);
      console.log(authenticated);
      this.newOwnerAllowed = authenticated && this.auth.hasAdmin();
    });
  }

  findOwners() {
    this.ownerService.findOwners(this.lastName).subscribe((owners) => this.owners = owners);
  }

  createOwner() {
    const editorRef = this.modalService.open(OwnerEditComponent);
    editorRef.componentInstance.owner = {};
    editorRef.result.then(
      (owner) => this.ownerService.create(owner).pipe(
        switchMap(() => this.ownerService.findOwners(this.lastName = ''))
      ).subscribe((owners) => this.owners = owners),
      (reason) => console.log(reason)
    );
  }
}
