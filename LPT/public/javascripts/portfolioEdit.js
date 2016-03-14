angular.module('portfolioEdit', [ 'ui.bootstrap' ]).controller(
		'portfolioEditController', function($http) {
			var portfolio = this;

			portfolio.error = 'no problem'

			$http.get("recordings").then(function(response) {
				portfolio.recordings = response.data;
				portfolio.recordings.push({
					id : 5,
					date : '2013.01.01'					
				})
			}, function(response) {
				portfolio.error = "Something went wrong";
			});

			portfolio.name = 'peters portolio';

			portfolio.newrecord = {
				id : 3,
				date : new Date(),
				isCollapsed : true,
				accounts : [ {
					id : 1,
					accounttypeid : 1,
					name : 'account1 name',
					description : 'this is my first account',
					defaultcurrency : 'HUF'
				} ]
			};

			portfolio.addRecording = function() {
				portfolio.recordings.push({
					id : 5,
					date : portfolio.newrecord.date,
					isCollapsed : true
				});
				portfolio.newrecord.isCollapsed = true;
			};

		});
