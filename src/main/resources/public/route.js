function route($routeProvider) {
	$routeProvider
		// route to dashboard
		.when('/', {
			templateUrl : 'patients.html',
			controller	: 'patients_ctrl'
		})
		.when('/patient/personal/', {
			templateUrl : 'patient_personal.html',
			controller : 'patient_personal_ctrl'
		})
		.when('/patient/active/', {
			templateUrl : 'patient_active.html',
			controller : 'patient_active_ctrl'
		});
}