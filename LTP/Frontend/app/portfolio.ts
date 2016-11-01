export class Account {
    id: number;
    name: string;
    description: string;
    defaultCurrency: string;
}


export class Portfolio {
    id: number;
    name: string;
    accounts: Account[];

}