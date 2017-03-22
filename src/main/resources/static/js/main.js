'use strict';
const app = angular.module('main', ['toaster']);
app.directive('overwriteEmail', function ()
{
	const EMAIL_REGEXP = /^[a-z0-9!#$%&'*+/=?^_`{|}~.-]+@example\.com$/i;
	return {
		require: '?ngModel',
		link: function (scope, elm, attrs, ctrl)
		{
			if (ctrl && ctrl.$validators.email.from && ctrl.$validators.email.to)
			{
				ctrl.$validators.email = function (modelValue)
				{
					return ctrl.$isEmpty(modelValue) || EMAIL_REGEXP.test(modelValue);
				};
			}
		}
	};
});
app.controller('emailController', EmailController);
EmailController.$inject = ['$scope', 'HttpService', 'toaster'];
function EmailController($scope, HttpService, toaster)
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
		else if ($scope.email.from === null)
		{
			toaster.pop('error', "Error", "enter addressee email!");
			return;
		}
		else if ($scope.email.from === null)
		{
			toaster.pop('error', "Error", "enter your email!");
			return;
		}

		let responseCode = HttpService.sendMail(url, $scope.email, config);
		if (responseCode === 200){
			toaster.pop('success', "Email", "send successfully!");
		}
	};
}