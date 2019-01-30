import { Visit } from './visit';
import { PetType } from './pet-type';

type PetData = { id: number, name: string, birthdate: Date, petType: PetType, visits: Visit[] };

export class Pet {
    id: number;
    name: string;
    birthdate: Date;
    petType: PetType;
    visits: Visit[];

    constructor({ id, name, birthdate, petType, visits }: PetData) {
        this.id = id;
        this.name = name;
        this.birthdate = new Date(birthdate);
        this.petType = petType && new PetType(petType);
        this.visits = visits && visits.map((visit) => new Visit(visit));
    }
}