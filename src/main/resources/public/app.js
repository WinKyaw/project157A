(function() {
	var app = angular.module('Medicloud', [ 'ngRoute' ]);
	app.config(["$routeProvider", route]);

	app
		.service('patients_service', ['$http', '$rootScope', patients_service])
		.service('patient_service', ['$http', '$rootScope', patient_service]);

	app
		.controller('patients_ctrl', 
			['$scope', 'patients_service', patients_ctrl])
		.controller('patient_personal_ctrl', 
			['$scope', 'patient_service', '$location', patient_personal_ctrl])
		.controller('patient_active_ctrl', 
			['$scope', 'patient_service', '$location', patient_active_ctrl]);
})();