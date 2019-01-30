import { Component, OnInit } from '@angular/core';
import { calendar } from 'octicons';
import { SafeHtml, DomSanitizer } from '@angular/platform-browser';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-visit-edit',
  templateUrl: './visit-edit.component.html',
  styleUrls: ['./visit-edit.component.scss']
})
export class VisitEditComponent implements OnInit {
  calendarIcon: SafeHtml;

  constructor(public modal: NgbActiveModal, private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.calendarIcon = this.sanitizer.bypassSecurityTrustHtml(calendar.toSVG());
  }
}
