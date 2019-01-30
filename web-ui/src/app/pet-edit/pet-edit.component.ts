import { Component, OnInit, Input } from '@angular/core';
import { Pet } from '../pet';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { calendar } from 'octicons';
import { PetService } from '../pet.service';
import { PetType } from '../pet-type';

@Component({
  selector: 'app-pet-edit',
  templateUrl: './pet-edit.component.html',
  styleUrls: ['./pet-edit.component.scss']
})
export class PetEditComponent implements OnInit {
  @Input() pet: Pet;
  calendarIcon: SafeHtml;
  petTypes: PetType[];
  comparePetType = (p1: PetType, p2: PetType) => p1 && p2 && p1.id === p2.id;

  constructor(public modal: NgbActiveModal, private sanitizer: DomSanitizer, private petService: PetService) { }

  ngOnInit() {
    this.calendarIcon = this.sanitizer.bypassSecurityTrustHtml(calendar.toSVG());
    this.petService.findPetTypes().subscribe((petTypes) => this.petTypes = petTypes);
  }
}
