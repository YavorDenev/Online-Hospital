package DigRz.OnlineHospital.repositories;

import DigRz.OnlineHospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
