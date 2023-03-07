package DigRz.OnlineHospital.services;

import DigRz.OnlineHospital.dto.DoctorReg;
import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.entities.Doctor;
import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.repositories.AppointmentRepository;
import DigRz.OnlineHospital.repositories.DoctorRepository;
import DigRz.OnlineHospital.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void saveDoctor(DoctorReg doctorReg) {
        Doctor doctor = new Doctor();

        doctor.setFirstName(doctorReg.getFirstName());
        doctor.setLastName(doctorReg.getLastName());
        doctor.setSpecialty(doctorReg.getSpecialty());
        User user = userRepository.getUserByUsername(doctorReg.getUsername());
        doctor.setUser(user);

        doctorRepository.save(doctor);
    }

    public List<Appointment> getSortedAppointments (Long doctorId, int sortCriteria, int sortMethod) {
        Doctor doctor = getDoctorById(doctorId);
        List<Appointment> appointments = appointmentRepository.findByDoctorOrderByPatientId(doctor);
        switch (sortCriteria) {
            case 1 -> appointments.sort(Comparator.comparing(Appointment::getPatientNames));
            case 2 -> appointments.sort(Comparator.comparing(Appointment::getTimeComparingKey));
            case 3 -> {}
        }
        if (sortMethod==2) Collections.reverse(appointments);

        return appointments;
    }

    public Doctor getDoctorById(Long doctorId) {
        Doctor doctor;
        if (doctorId != 0) {
            doctor = doctorRepository.findById(doctorId).get();
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.getUserByUsername((auth.getName()));
            doctor = doctorRepository.findByUser(user);
        }
        return doctor;
    }

    public String getDoctorInfo(Long doctorId) {
        Doctor doctor = getDoctorById(doctorId);
        return " " + doctor.getFirstName() + " " + doctor.getLastName() + " with specialty " + doctor.getSpecialty() + " and ID " + doctor.getId();
    }

    public void deleteDoctorAndHisAppointments(Long id) {
        List<Appointment> appointmentList = appointmentRepository.findByDoctorId(id);
        appointmentRepository.deleteAll(appointmentList);
        User user = getDoctorById(id).getUser();
        doctorRepository.deleteById(id);
        userRepository.delete(user);
    }

}
