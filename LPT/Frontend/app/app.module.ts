import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule}   from '@angular/forms';
import {AppComponent}   from './app.component';
import {PortfolioComponent} from './portfolio.component';
import {QuoteComponent} from './quote.component';
import {DataService} from "./services/data.service";
import {HttpModule} from '@angular/http';
import {Configuration} from "./app.constants";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [BrowserModule, FormsModule, HttpModule, NgbModule.forRoot()],
    declarations: [AppComponent, PortfolioComponent, QuoteComponent],
    providers: [DataService, Configuration],
    bootstrap: [AppComponent]
})

export class AppModule {

}
