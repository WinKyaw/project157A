select last_name, first_name, birthdate, email, phone, MIN(message) from patient pa
INNER JOIN person pe ON pa.person_id = pe.person_id
LEFT JOIN  contact c ON pe.person_id = c.person_id
LEFT JOIN note n ON pa.patient_id = n.patient_id
GROUP BY pa.patient_id