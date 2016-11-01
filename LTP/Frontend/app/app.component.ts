import {Component, OnInit, Input, Output} from '@angular/core';
import {Portfolio, Account} from './portfolio';
import {Quote} from './quote';
import {DataService} from "./data.service";

@Component({
    selector: 'my-app',
    providers: [DataService],
    template: `
    <h2>{{title}}</h2>    
    <h3>
        <b>{{portfolio.name}}</b>
        <b>{{quote?.author}}</b>
    </h3>    
    <my-portfolio [portfolio]="portfolio"></my-portfolio>
    <div *ngIf="quote"><my-quote [quote]="quote"></my-quote></div>
    
`

})


export class AppComponent implements OnInit {
    @Input()
    quote: Quote;

    ngOnInit(): void {
        this.getQuote();


    }

    getQuote(): void {
        this._dataService
            .GetSingle()
            .subscribe((data: Quote)=>this.quote = data,
                error=>console.log(error),
                () => console.log('get quote complete'));
    }

    constructor(private _dataService: DataService) {

    }


    title = 'Lazy Portfolio Tracker';
    copyright = 'LPT team';
    portfolio: Portfolio = {
        id: 1,
        name: 'Peter\'s portfolio',
        accounts: Account[1] = [
            {
                id: 1,
                name: 'TBSZ2',
                description: 'TBSZ szamla leirasa',
                defaultCurrency: 'HUF'
            },
            {
                id: 2,
                name: 'TBSZ2015',
                description: 'TBSZ szamla leirasa',
                defaultCurrency: 'HUF'

            },
            {
                id: 3,
                name: 'TBSZ2017',
                description: 'TBSZ szamla leirasa',
                defaultCurrency: 'HUF'

            }
        ]
    };


}


