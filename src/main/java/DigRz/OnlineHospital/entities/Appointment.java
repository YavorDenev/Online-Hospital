package DigRz.OnlineHospital.entities;

import DigRz.OnlineHospital.constants.Examination;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

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
       // @Column(name = "date", nullable = false, columnDefinition = "DATE")
        private LocalDate date1;
        @NotNull
        @Column(name = "time", nullable = false, columnDefinition = "TIME")
        private LocalTime time;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Patient getPatient() {
                return patient;
        }

        public void setPatient(Patient patient) {
                this.patient = patient;
        }

        public Doctor getDoctor() {
                return doctor;
        }

        public void setDoctor(Doctor doctor) {
                this.doctor = doctor;
        }

        public Examination getExamination() {
                return examination;
        }

        public void setExamination(Examination examination) {
                this.examination = examination;
        }

        public LocalDate getDate1() {
                return date1;
        }

        public void setDate1(LocalDate date1) {
                this.date1 = date1;
        }

        public LocalTime getTime() {
                return time;
        }

        public void setTime(LocalTime time) {
                this.time = time;
        }

    }
