package DigRz.OnlineHospital.repositories;

import DigRz.OnlineHospital.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
