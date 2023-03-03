package DigRz.OnlineHospital.services;

import DigRz.OnlineHospital.dto.DoctorReg;
import DigRz.OnlineHospital.entities.Doctor;
import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.repositories.DoctorRepository;
import DigRz.OnlineHospital.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveDoctor(DoctorReg doctorReg) {
        Doctor doctor = new Doctor();

        doctor.setFirstName(doctorReg.getFirstName());
        doctor.setLastName(doctorReg.getLastName());
        doctor.setSpecialty(doctorReg.getSpecialty());
        User user = userRepository.getUserByUsername(doctorReg.getUsername());
        doctor.setUser(user);

        doctorRepository.save(doctor);
    }



}
