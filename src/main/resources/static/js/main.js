'use strict';
const app = angular.module('main', ['toaster']);
app.directive('overwriteEmail', function() {
	var EMAIL_REGEXP = /^[a-z0-9!#$%&'*+/=?^_`{|}~.-]+@example\.com$/i;

	return {
		require: '?ngModel',
		link: function(scope, elm, attrs, ctrl) {
			// only apply the validator if ngModel is present and AngularJS has added the email validator
			if (ctrl && ctrl.$validators.email) {

				// this will overwrite the default AngularJS email validator
				ctrl.$validators.email = function(modelValue) {
					return ctrl.$isEmpty(modelValue) || EMAIL_REGEXP.test(modelValue);
				};
			}
		}
	};
});
app.controller('emailController', function ($scope, $http, toaster)
{
	$scope.email =
			{
				from: '',
				subject: '',
				to: '',
				content: '',
			};
	let url = "/send";
	let config =
			{
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
		$http.post(url, $scope.email, config)
				.then(
						function (response)
						{
							if (response.status == 200)
							{
								toaster.pop('success', "Your letter was send", "successfully");
							}
						}
						,
						function (response)
						{
							return response;
						}
				);
	};
});
