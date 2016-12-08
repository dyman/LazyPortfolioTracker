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
var portfolio_1 = require("./portfolio");
var quote_1 = require("./quote");
var data_service_1 = require("./services/data.service");
var AppComponent = (function () {
    function AppComponent(_dataService) {
        this._dataService = _dataService;
        this.quote = {
            quote: 'bullshit',
            author: 'peter'
        };
        this.title = 'Lazy Portfolio Tracker';
        this.copyright = 'LPT team';
        this.portfolio = {
            id: 1,
            name: 'Peter\'s portfolio',
            accounts: portfolio_1.Account[1] = [
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
    AppComponent.prototype.ngOnInit = function () {
        this.getQuote();
    };
    AppComponent.prototype.getQuote = function () {
        var _this = this;
        this._dataService
            .getSingleQuote().then(function (response) { return _this.quote = response; });
    };
    return AppComponent;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", quote_1.Quote)
], AppComponent.prototype, "quote", void 0);
AppComponent = __decorate([
    core_1.Component({
        selector: 'my-app',
        providers: [data_service_1.DataService],
        templateUrl: 'app/app.component.html'
    }),
    __metadata("design:paramtypes", [data_service_1.DataService])
], AppComponent);
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map