package DigRz.OnlineHospital.services;

import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.entities.Doctor;
import DigRz.OnlineHospital.entities.Patient;
import DigRz.OnlineHospital.repositories.AppointmentRepository;
import DigRz.OnlineHospital.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;


@Service
public class GroupingService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DoctorRepository doctorRepository;
    public HashSet<Patient> findPatientsByDoctor(Long doctorId) {
        HashSet<Patient> patientsSet = new HashSet<>();

        for (Appointment app:appointmentRepository.findAll()) {
            if (app.getDoctor().getId() == doctorId){
                patientsSet.add(app.getPatient());
            }
        }

        return patientsSet;
    }
    public Map<Doctor,Long> findPatientsCountByDoctor(){
        Map<Doctor,Long> patientsCount = new LinkedHashMap<>();
        for (Doctor dr:doctorRepository.findAll()) {
            patientsCount.put(dr,findPatientsByDoctor(dr.getId()).stream().count());
        }
        return patientsCount;
    }
}
