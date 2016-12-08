import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import 'rxjs/add/operator/map'
import {Observable} from 'rxjs/Rx';
import {Quote} from '../quote';
import {Configuration} from '../app.constants';

@Injectable()
export class DataService {

    private actionUrl: string;
    private headers: Headers;

    constructor(private _http: Http, private _configuration: Configuration) {
        console.log(_configuration.Server)
        this.actionUrl = _configuration.ServerWithApiUrl + 'quote';
        console.log("dataservice created with action url: ", this.actionUrl, this._http);
        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/json');
        this.headers.append('Accept', 'application/json');
    }


    public getSingleQuote(): Promise<Quote> {
        return this._http.get(this.actionUrl).toPromise()
            .then((response: Response) => {
                console.log("server response: " + (<Quote>response.json()).quote);
                return <Quote>response.json()
            });
            //.catch(this.handleError);
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
