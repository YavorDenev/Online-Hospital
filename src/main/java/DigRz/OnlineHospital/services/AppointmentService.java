package DigRz.OnlineHospital.services;

import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.entities.Doctor;
import DigRz.OnlineHospital.entities.Patient;
import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.repositories.AppointmentRepository;
import DigRz.OnlineHospital.repositories.DoctorRepository;
import DigRz.OnlineHospital.repositories.PatientRepository;
import DigRz.OnlineHospital.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public String verifyIfHourIsBusy(Appointment appointment) {
        String date = appointment.getMyDate();
        String time = appointment.getMyTime();
        if ( ! (appointmentRepository
                .findByPatientAndMyDateAndMyTime(getCurrentPatient(), date, time)
                .isEmpty())
        ) {
            return "You have an appointment reserved for the same time. Choose another hour!";
        }
        if ( ! (appointmentRepository
                .findByDoctorAndMyDateAndMyTime(appointment.getDoctor(), date, time)
                .isEmpty())
            ) {
            return "This doctor is busy at this time. Choose another hour!";
        }
        return "";
    }

    public void saveNewAppointment(Appointment appointment) {
        appointment.setPatient(getCurrentPatient());
        appointmentRepository.save(appointment);
    }

    public Patient getCurrentPatient () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUserByUsername((auth.getName()));
        return patientRepository.findByUser(user);
    }

    public List<Appointment> getSortedAppointments (Long doctorId, int sortCriteria, int sortMethod) {
        Doctor doctor = doctorRepository.findById(doctorId).get();
        List<Appointment> appointments = appointmentRepository.findByDoctorOrderByPatientId(doctor);
        switch (sortCriteria) {
            case 1 -> appointments.sort(Comparator.comparing(Appointment::getPatientNames));
            case 2 -> appointments.sort(Comparator.comparing(Appointment::getTimeComparingKey));
            case 3 -> {}
        }
        if (sortMethod==2) Collections.reverse(appointments);

        return appointments;
    }

}
