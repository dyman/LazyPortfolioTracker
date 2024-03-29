"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
require("rxjs/add/operator/map");
var Rx_1 = require("rxjs/Rx");
var app_constants_1 = require("../app.constants");
var DataService = (function () {
    function DataService(_http, _configuration) {
        this._http = _http;
        this._configuration = _configuration;
        console.log(_configuration.Server);
        this.actionUrl = _configuration.ServerWithApiUrl;
        console.log("dataservice created with action url: ", this.actionUrl, this._http);
        this.headers = new http_1.Headers();
        this.headers.append('Content-Type', 'application/json');
        this.headers.append('Accept', 'application/json');
    }
    DataService.prototype.getSingleQuote = function () {
        return this._http.get(this.actionUrl + 'quote').toPromise()
            .then(function (response) {
            console.log("server response: " + response.json().quote);
            return response.json();
        });
        //.catch(this.handleError);
    };
    DataService.prototype.getAccountTypes = function () {
        return this._http.get(this.actionUrl + 'accounttypes').toPromise()
            .then(function (response) {
            //console.log("server response: "(<Accounttypes[]>response.json()));
            return response.json();
        });
    };
    DataService.prototype.handleError = function (error) {
        console.error(error);
        return Rx_1.Observable.throw(error.json().error || 'Server error');
    };
    return DataService;
}());
DataService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, app_constants_1.Configuration])
], DataService);
exports.DataService = DataService;
//# sourceMappingURL=data.service.js.map