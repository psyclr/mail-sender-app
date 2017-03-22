app.service('HttpService', HttpService);
HttpService.$inject = ['$http'];
function HttpService($http)
{
	function _sendMail(url, email, config)
	{
		$http.post(url, email, config).then(_onSendMailCompleted, _onSendMailCompleted);
	}

	function _onSendMailCompleted(response)
	{
		return response.status;
	}

	return {
		sendMail:_sendMail
	}
}
