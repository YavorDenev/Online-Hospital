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
    public HashSet<Patient> findPatientsByDate(String date) {
        HashSet<Patient> patientsSet = new HashSet<>();

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

        for (Appointment app:appointmentRepository.findAll()) {
            Specialty specialty = Specialty.valueOf(app.getDoctor().getSpecialty().toUpperCase());
            HashSet<Patient> patients = patientsEnumMap.get(specialty);
            if (patients == null) {
                patients = new HashSet<>();
            }
            patients.add(app.getPatient());
            patientsEnumMap.put(specialty, patients);
        }
        return patientsEnumMap;
    }
    public LinkedHashMap<String,Integer> findPatientsCountByDepartment(){
        LinkedHashMap<String,Integer> patientsCount = new LinkedHashMap<String,Integer>();

        for (Specialty spec :findPatientsByDepartment().keySet()) {
            patientsCount.put(spec.getValue(),findPatientsByDepartment().get(spec).size());
            System.out.println(spec +" " + findPatientsByDepartment().get(spec).size());
        }
        return patientsCount;
    }
}
