import { Component, Input } from '@angular/core';

import { Portfolio } from './portfolio';


@Component({
    selector: 'my-portfolio',
    templateUrl: 'app/templates/portfolio.component.html'

})

export class PortfolioComponent {
  @Input()
  portfolio: Portfolio;
}
