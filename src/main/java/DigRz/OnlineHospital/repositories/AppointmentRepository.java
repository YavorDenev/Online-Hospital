package DigRz.OnlineHospital.repositories;

import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.entities.Doctor;
import DigRz.OnlineHospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment>  findByPatient(@RequestParam Patient patient);

    List<Appointment> findByDoctorAndMyDateAndMyTime(Doctor doctor, String myDate, String myTime);

    List<Appointment> findByPatientAndMyDateAndMyTime(Patient patient, String myDate, String myTime);

    List<Appointment> findByDoctorOrderByPatientId(Doctor doctor);

//    @Query("SELECT a FROM Appointment a" +
//            "JOIN doctor ON doctor.id=appointment.doctor_id" +
//            "WHERE a.doctor = :doctor" +
//            "ORDER BY date, time")
//    List<Appointment> findByDoctorOrderByMyDateAndMyTime(Doctor doctor);


//    List<Appointment> findByDoctorOrderByPatientNames(Doctor doctor);


}


