import {Component, Input} from '@angular/core';

import {Quote} from './quote';


@Component({
    selector: 'my-quote',
    templateUrl: 'app/templates/quote.component.html'

})

export class QuoteComponent {
    @Input()
    quote: Quote;
}
