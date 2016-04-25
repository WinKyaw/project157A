function patients_ctrl($scope, service) {
	$scope.patients = [];
	$scope.newPatient = {
		first_name: '',
		last_name: '',
		gender: '',
		birthdate: '',
		phone: '',
		email: ''
	};

	$scope.linkToPersonal = function(patient) {
		return '#/patient/personal/?patient_id=' + patient.patient_id;
	}
	$scope.linkToActive = function(patient) {
		return '#/patient/active/?patient_id=' + patient.patient_id;
	}

	$scope.addNewPatient = function() {
		$('#newPatientModal').off('hidden.bs.modal').on('hidden.bs.modal', function() {
			$scope.newPatient = {
				first_name: '',
				last_name: '',
				gender: '',
				birthdate: '',
				phone: '',
				email: ''
			};
		});

		service.post($scope.newPatient).then(function(result) {
			if (result) {
				$('#newPatientModal').modal('hide');
				service.fetch().then(function() {
					$scope.patients = service.model;
				});
			}
		});
	}

	service.fetch().then(function() {
		$scope.patients = service.model;
	});

	
}

function patient_personal_ctrl($scope, service, $location) {
	$scope.personal = {};
	$scope.contact = {};
	$scope.notes = [];
	$scope.newNotes = [];

	var patient_id = $location.search().patient_id;

	service.fetch(patient_id).then(function() {
		$scope.personal = service.model.personal;
		$scope.contact = service.model.contact;
		$scope.notes = service.model.notes;
	});

	$scope.addNote = function() {
		$scope.newNotes = [{ "patient_id" : patient_id, "message": "" }];
	}

	$scope.cancelNewNote = function() {
		$scope.newNotes = [];
	}

	$scope.saveNewNote = function() {
		if ($scope.newNotes.length > 0) {
			service.postNewNote($scope.newNotes[0])
				.then(function(result) {
					if (result) {
						$scope.newNotes = [];
						service.fetch(patient_id).then(function() {
							$scope.notes = service.model.notes;
						});
					}

				});
		}
	}
}

function patient_active_ctrl($scope, service, $location) {
	$scope.active_list = [];
	$scope.newActives = [];

	var patient_id = $location.search().patient_id;

	service.fetchActive(patient_id).then(function() {
		$scope.patient = service.model.personal;
		$scope.active_list = service.model.active_list;
	});

	$scope.addActive = function() {
		$scope.newActives.push({
	    	"patient_id" : patient_id,
	    	"concept_id" : 0,
	    	"comments"	 : ""
		});
	}

	$scope.cancelActive = function($index) {
		$scope.newActives.splice($index, 1);
	}

	$scope.saveActive = function(active, $index) {
		service.postNewActive(active).then(function(result) {
			if (result) {
				service.fetchActive(patient_id).then(function() {
					$scope.newActives.splice($index, 1);
					$scope.active_list = service.model.active_list;
				});
			}
		});
	}
}