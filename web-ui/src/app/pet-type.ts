type PetTypeData = { id: number, name: string };

export class PetType {
    id: number;
    name: string;

    constructor({ id, name }: PetTypeData) {
        this.id = id;
        this.name = name;
    }
}