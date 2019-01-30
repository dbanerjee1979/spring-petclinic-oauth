type SpecialtyData = { name: string }
type VetData = { firstName: string, lastName: string, specialties: SpecialtyData[] }

export class Vet {
    fullName: string;
    specialties: string;

    constructor({ firstName, lastName, specialties} : VetData) {
        this.fullName = firstName + ' ' + lastName;
        this.specialties = specialties.length == 0 ? 'None' : specialties.map((sp) => sp.name).join(', ');
    }
}