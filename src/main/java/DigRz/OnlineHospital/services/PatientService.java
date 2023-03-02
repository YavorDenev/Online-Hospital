package DigRz.OnlineHospital.services;

import DigRz.OnlineHospital.dto.PatientReg;
import DigRz.OnlineHospital.entities.Patient;
import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.repositories.PatientRepository;
import DigRz.OnlineHospital.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserRepository userRepository;

    public void savePatient(PatientReg patientReg) {
        Patient patient = new Patient();

        patient.setFirstName(patientReg.getFirstName());
        patient.setLastName(patientReg.getLastName());
        patient.setAge(patientReg.getAge());
        User user = userRepository.getUserByUsername(patientReg.getUsername());
        patient.setUser(user);

        patientRepository.save(patient);
    }


}
