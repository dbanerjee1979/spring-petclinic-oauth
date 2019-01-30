type VisitData = { visitDate: Date, details: string };

export class Visit {
    visitDate: Date;
    details: string;

    constructor({ visitDate, details }: VisitData) {
        this.visitDate = visitDate;
        this.details = details;
    }
}