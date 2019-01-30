import { Component, OnInit, Input } from '@angular/core';
import { Owner } from '../owner';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-owner-edit',
  templateUrl: './owner-edit.component.html',
  styleUrls: ['./owner-edit.component.scss']
})
export class OwnerEditComponent {
  @Input() owner: Owner;

  constructor(public modal: NgbActiveModal) { }
}
