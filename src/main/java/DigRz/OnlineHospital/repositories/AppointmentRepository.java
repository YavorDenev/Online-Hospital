package DigRz.OnlineHospital.repositories;

import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.entities.Doctor;
import DigRz.OnlineHospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment>  findByPatient(@RequestParam Patient patient);

    List<Appointment> findByDoctorAndMyDateAndMyTime(Doctor doctor, String myDate, String myTime);

    List<Appointment> findByPatientAndMyDateAndMyTime(Patient patient, String myDate, String myTime);
}
