angular.module('portfolioEdit', [ 'ui.bootstrap' ]).controller(
		'portfolioEditController', function() {
			var portfolio = this;

			portfolio.name = 'peters portolio';
			
			portfolio.newrecord = {
				id: 3,
				date : new Date(),
				isCollapsed : true,
				accounts : [ {
					id : 1,
					accounttypeid : 1,
					name: 'account1 name',
					description : 'this is my first account',
					defaultcurrency : 'HUF'					
				} ]
			};

			portfolio.recordings = [ {
				id : 1,
				date : '2016.01.01',
				isCollapsed : true

			}, {
				id: 2,
				date : '2015.01.01',
				isCollapsed : true

			} ];
			

		});
