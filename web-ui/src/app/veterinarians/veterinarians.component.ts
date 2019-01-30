import { Component, OnInit } from '@angular/core';
import { VetsService } from '../vets.service';
import { Vet } from '../vet';

@Component({
  selector: 'app-veterinarians',
  templateUrl: './veterinarians.component.html',
  styleUrls: ['./veterinarians.component.scss']
})
export class VeterinariansComponent implements OnInit {
  vets: Vet[];
        
  constructor(private vetsService: VetsService) { }

  ngOnInit() {
    this.vetsService.findAll().subscribe((vets) => this.vets = vets);
  }
}
