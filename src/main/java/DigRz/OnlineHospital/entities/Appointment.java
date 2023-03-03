package DigRz.OnlineHospital.entities;

import DigRz.OnlineHospital.constants.Examination;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name="appointments")
public class Appointment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
        private Long id;

        @NotNull
        @ManyToOne
        @JoinColumn(name = "patient_id", nullable = false)
        Patient patient;

        @NotNull
        @ManyToOne
        @JoinColumn(name = "doctor_id", nullable = false)
        Doctor doctor;

        @NotNull
        @Enumerated(EnumType.STRING)
        private Examination examination;

        @NotNull
        @Column
        private LocalDateTime dateTime;
    }
