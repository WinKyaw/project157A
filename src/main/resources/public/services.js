function patients_service($http, $rootScope) {
	/* private properties*/
	var onSuccess;
	var onFailure;

	/* public model properties*/
	var service = function() {
		this.model = {};

		this.error;
		this.serverMsg;
	}

	service.prototype = {
		constructor: this,

		fetch: function() {
			var that = this;
			$http
				.get('http://localhost:8080/patients/personal')
				.then(function(response) {
					if (response.data) {
						try {
							that.model = response.data;
							onSuccess();
						} catch(err) {
							onFailure();
							console.log(response);
						}
					}
					
				}, function(response) {
					onFailure();
				});
			return that;
		}, 

		post: function(data) {
			var that = this;
			$http
				.post('http://localhost:8080/patient/new', data)
				.then(function(response) {
					console.log(response);
					if (response.data) {
						onSuccess(response.data);
					}
					
				}, function(response) {
					onFailure();
				});
			return that;
		},

		then: function(successCallback, failureCallback) {
			if (typeof successCallback == 'function')
				onSuccess = successCallback;
			else
				coonsole.log('callback must be a function.');

			// no warning for onFailure because it's optional
			if (typeof failureCallback == 'function')
				onFailure = failureCallback;
		}
	}


	return new service;
}

function patient_service($http, $rootScope) {
	/* private properties*/
	var onSuccess;
	var onFailure;

	/* public model properties*/
	var service = function() {
		this.model = {};

		this.error;
		this.serverMsg;
	}

	service.prototype = {
		constructor: this,

		fetch: function(id) {
			var that = this;
			$http
				.get('http://localhost:8080/patient/' + id + '/personal')
				.then(function(response) {
					if (response.data) {
						try {
							that.model = response.data;
							onSuccess();
						} catch(err) {
							onFailure();
							console.log(response);
						}
					}
					
				}, function(response) {
					onFailure();
				});
			return that;
		}, 

		fetchActive: function(id) {
			var that = this;
			$http
				.get('http://localhost:8080/patient/' + id + '/active')
				.then(function(response) {
					if (response.data) {
						try {
							that.model = response.data;
							onSuccess();
						} catch(err) {
							onFailure();
							console.log(response);
						}
					}
					
				}, function(response) {
					onFailure();
				});
			return that;
		},

		postNewNote: function(data) {
			var that = this;
			$http
				.post('http://localhost:8080/patient/note/new', data)
				.then(function(response) {
					console.log(response);
					if (response.data) {
						onSuccess(response.data);
					}
					
				}, function(response) {
					onFailure();
				});
			return that;
		},

		postNewActive: function(data) {
			var that = this;
			$http
				.post('http://localhost:8080/patient/active/new', data)
				.then(function(response) {
					console.log(response);
					if (response.data) {
						onSuccess(response.data);
					}
					
				}, function(response) {
					onFailure();
				});
			return that;
		},

		then: function(successCallback, failureCallback) {
			if (typeof successCallback == 'function')
				onSuccess = successCallback;
			else
				coonsole.log('callback must be a function.');

			// no warning for onFailure because it's optional
			if (typeof failureCallback == 'function')
				onFailure = failureCallback;
		}
	}


	return new service;
}