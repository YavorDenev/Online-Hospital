package DigRz.OnlineHospital.services;

import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.entities.Patient;
import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.repositories.AppointmentRepository;
import DigRz.OnlineHospital.repositories.PatientRepository;
import DigRz.OnlineHospital.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;

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

}
