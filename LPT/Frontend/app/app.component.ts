import {Component, OnInit, Input} from '@angular/core';
import {Portfolio, Account} from './portfolio';
import {Quote} from './quote';
import {DataService} from "./services/data.service";
import {Accounttypes} from "./accounts/accounttypes";


@Component({
    selector: 'my-app',
    providers: [DataService],
    templateUrl: 'app/app.component.html'

})


export class AppComponent implements OnInit {
    @Input()
    quote: Quote;
    //= {
    //     quote: 'bullshit',
    //     author: 'peter'
    // };

    accounttypes: Accounttypes[];

    ngOnInit(): void {
        this.getQuote();
    }

    getQuote(): void {
        this._dataService
            .getSingleQuote().then(response => this.quote = response).catch(response => this.quote = {
            quote: 'bullshit',
            author: 'peti'
        })
        this._dataService
            .getAccountTypes().then(response => this.accounttypes = response)

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


