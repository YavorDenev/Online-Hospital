package DigRz.OnlineHospital.repositories;

import DigRz.OnlineHospital.entities.Doctor;
import DigRz.OnlineHospital.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByUser(User user);

}
