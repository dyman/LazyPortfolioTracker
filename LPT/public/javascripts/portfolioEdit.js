angular.module('portfolioEdit', []).controller('portfolioEditController',
		function() {
			var portfolio = this;
			portfolio.todos = [ {
				text : 'learn angular',
				done : true
			}, {
				text : 'build an angular app',
				done : false
			} ];

			portfolio.addTodo = function() {
				portfolio.todos.push({
					text : portfolio.todoText,
					done : false
				});
				portfolio.todoText = '';
			};

			portfolio.remaining = function() {
				var count = 0;
				angular.forEach(portfolio.todos, function(todo) {
					count += todo.done ? 0 : 1;
				});
				return count;
			};

			portfolio.archive = function() {
				var oldTodos = todoList.todos;
				portfolio.todos = [];
				angular.forEach(oldTodos, function(todo) {
					if (!todo.done)
						portfolio.todos.push(todo);
				});
			};
		});