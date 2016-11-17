import {Component, Input} from '@angular/core';

import {Quote} from './quote';


@Component({
    selector: 'my-quote',
    templateUrl: 'app/quote.component.html'

})

export class QuoteComponent {
    @Input()
    quote: Quote;
}
