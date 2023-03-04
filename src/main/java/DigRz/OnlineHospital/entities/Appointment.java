package DigRz.OnlineHospital.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="appointments")
public class Appointment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
        private Long id;

        //@NotNull
        @ManyToOne
        @JoinColumn(name = "patient_id", nullable = false)
        Patient patient;

        @NotNull
        @ManyToOne
        @JoinColumn(name = "doctor_id", nullable = false)
        Doctor doctor;

        @NotNull
        @Column(length = 100, nullable = false)
        private String examination;
        @NotNull
        @Column(name = "date", nullable = false, columnDefinition = "DATE")
        private String myDate;
        @NotNull
        @Column(name = "time", nullable = false, columnDefinition = "TIME")
        private String myTime;

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

        public String getExamination() {
                return examination;
        }

        public void setExamination(String examination) {
                this.examination = examination;
        }

        public String getMyDate() {
                return myDate;
        }

        public void setMyDate(String myDate) {
                this.myDate = myDate;
        }

        public String getMyTime() {
                return myTime;
        }

        public void setMyTime(String myTime) {
                this.myTime = myTime;
        }
}
