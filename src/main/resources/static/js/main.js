'use strict';
const app = angular.module('main', ['toaster']);
app.directive('overwriteEmail', function ()
{
	const EMAIL_REGEXP = /^[a-z0-9!#$%&'*+/=?^_`{|}~.-]+@example\.com$/i;
	return {
		require: '?ngModel',
		link: function (scope, elm, attrs, ctrl)
		{
			if (ctrl && ctrl.$validators.email)
			{
				ctrl.$validators.email = function (modelValue)
				{
					return ctrl.$isEmpty(modelValue) || EMAIL_REGEXP.test(modelValue);
				};
			}
		}
	};
});
app.controller('emailController', MainController);
MainController.$inject = ['$scope', 'HttpService', 'toaster'];
function MainController($scope, HttpService, toaster)
{
	$scope.email = {
		from: '',
		subject: '',
		to: '',
		content: '',
	};
	let url = "/send";
	let config = {
		headers: {
			'Content-Type': 'application/json;charset=utf-8;'
		}
	};
	$scope.sendEmailToServer = function ()
	{
		if ($scope.email.from === $scope.email.to)
		{
			toaster.pop('error', "Error", "emails shouldn't be the same!");
			return;
		}
		HttpService.sendMail(url, $scope.email, config);
	};
}