package DigRz.OnlineHospital.services;

import DigRz.OnlineHospital.constants.Specialty;
import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.entities.Doctor;
import DigRz.OnlineHospital.entities.Patient;
import DigRz.OnlineHospital.repositories.AppointmentRepository;
import DigRz.OnlineHospital.repositories.DoctorRepository;
import DigRz.OnlineHospital.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class GroupingService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    Utils utils;

    public Set<Patient> findPatientsByDoctor(Long doctorId) {
        Set<Patient> patientsSet = new HashSet<>();

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

    public Set<Patient> findPatientsByDate(String date) {
        Set<Patient> patientsSet = new HashSet<>();

        for (Appointment app:appointmentRepository.findAll()) {
            if (app.getMyDate().equals(date)){
                patientsSet.add(app.getPatient());
            }
        }
        return patientsSet;
    }

    public Map<String,Long> findPatientsCountByDate(){
        Map<String,Long> patientsCount = new LinkedHashMap<>();

        for (LocalDate day:utils.generateListOfDays()) {
            patientsCount.put(day.toString(),findPatientsByDate(day.toString()).stream().count());
        }
        return patientsCount;
    }

    public EnumMap<Specialty,HashSet<Patient>> findPatientsByDepartment() {
        EnumMap<Specialty,HashSet<Patient>> patientsEnumMap = new EnumMap<Specialty,HashSet<Patient>>(Specialty.class);

        for (Specialty sp: Specialty.values()){
            HashSet<Patient> patients = new HashSet<>();
            patientsEnumMap.put(sp, patients);
        }
        for (Appointment app:appointmentRepository.findAll()) {
            Specialty specialty = Specialty.valueOf(app.getDoctor().getSpecialty().toUpperCase());
            HashSet<Patient> patients = patientsEnumMap.get(specialty);
            patients.add(app.getPatient());
            patientsEnumMap.put(specialty, patients);
        }
        return patientsEnumMap;
    }

    public Map<String,Integer> findPatientsCountByDepartment(){
        Map<String,Integer> patientsCount = new LinkedHashMap<String,Integer>();

        for (Specialty spec :findPatientsByDepartment().keySet()) {
            patientsCount.put(spec.getValue(),findPatientsByDepartment().get(spec).size());
        }
        return patientsCount;
    }
}
