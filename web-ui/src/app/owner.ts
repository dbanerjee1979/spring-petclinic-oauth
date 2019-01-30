import { Pet } from './pet';

type OwnerData = { 
    id: number, firstName: string, lastName: string, username: string, address: string, 
    city: string, telephone: string, pets: Pet[] }

export class Owner {
    id: number;
    firstName: string;
    lastName: string;
    username: string;
    fullName: string;
    address: string;
    city: string;
    telephone: string;
    pets: Pet[];
    petNames: string;

    constructor({ id, firstName, lastName, username, address, city, telephone, pets }: OwnerData) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = this.firstName + ' ' + this.lastName;
        this.username = username;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets.map((pet: any) => new Pet(pet));
        this.petNames = this.pets.map((pet) => pet.name).join(', ');
    }
}