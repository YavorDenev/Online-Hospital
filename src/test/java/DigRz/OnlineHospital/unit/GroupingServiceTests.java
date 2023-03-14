package DigRz.OnlineHospital.unit;

import DigRz.OnlineHospital.constants.Specialty;
import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.entities.Doctor;
import DigRz.OnlineHospital.entities.Patient;
import DigRz.OnlineHospital.repositories.AppointmentRepository;
import DigRz.OnlineHospital.repositories.DoctorRepository;
import DigRz.OnlineHospital.services.GroupingService;
import DigRz.OnlineHospital.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupingServiceTests {
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        //this.initialSetup();
    }

    @Mock
    AppointmentRepository appointmentRepository;

    @Mock
    DoctorRepository doctorRepository;

    @Mock
    Utils utils;
    @InjectMocks
    Doctor doctor1;
    @InjectMocks
    Doctor doctor2;
    @InjectMocks
    Doctor doctor3;
    @InjectMocks
    Patient patient1;
    @InjectMocks
    Patient patient2;
    @InjectMocks
    Appointment appointment1;
    @InjectMocks
    Appointment appointment2;
    @InjectMocks
    Appointment appointment3;
    @InjectMocks
    Appointment appointment4;
    @InjectMocks
    GroupingService groupingService;

    @Test
    public void testFindPatientsByDate() {
        String day1 = "2023-03-08";
        String day2 = "2023-03-07";
        appointment1.setMyDate(day1);
        appointment2.setMyDate(day1);
        appointment3.setMyDate(day1);
        appointment4.setMyDate(day2);
        appointment1.setPatient(patient1);
        appointment2.setPatient(patient2);
        appointment3.setPatient(patient1);
        appointment4.setPatient(patient1);
        List<Appointment> appointments = new ArrayList<>(Arrays.asList(appointment1, appointment2, appointment3, appointment4));

        when(appointmentRepository.findAll()).thenReturn(appointments);

        Set<Patient> patients = groupingService.findPatientsByDate(day1);

        assertEquals(2, patients.size());
    }

    @Test
    public void testFindPatientsCountByDate() {
        LocalDate day1 = LocalDate.parse("2023-03-08");
        LocalDate day2 = LocalDate.parse("2023-03-07");
        appointment1.setMyDate(day1.toString());
        appointment2.setMyDate(day1.toString());
        appointment3.setMyDate(day1.toString());
        appointment4.setMyDate(day2.toString());
        appointment1.setPatient(patient1);
        appointment2.setPatient(patient2);
        appointment3.setPatient(patient1);
        appointment4.setPatient(patient1);
        List<Appointment> appointments = new ArrayList<>(Arrays.asList(appointment1, appointment2, appointment3, appointment4));

        when(appointmentRepository.findAll()).thenReturn(appointments);
        List<LocalDate> days = new ArrayList<>();
        days.add(day1);
        days.add(day2);
        when(utils.generateListOfDays()).thenReturn(days);
        Map<String, Long> patientsCount = groupingService.findPatientsCountByDate();

        assertEquals(2, patientsCount.size());
        assertEquals(2L, (long) patientsCount.get(day1.toString()));
    }

    @Test
    public void testFindPatientsByDepartment() {
        doctor1.setSpecialty(Specialty.CARDIOLOGY.name());
        doctor2.setSpecialty(Specialty.CARDIOLOGY.name());
        doctor3.setSpecialty(Specialty.DERMATOLOGY.name());
        appointment1.setPatient(patient1);
        appointment2.setPatient(patient2);
        appointment3.setPatient(patient1);
        appointment4.setPatient(patient1);
        appointment1.setDoctor(doctor1);
        appointment2.setDoctor(doctor1);
        appointment3.setDoctor(doctor2);
        appointment4.setDoctor(doctor3);
        List<Appointment> appointments = new ArrayList<>(Arrays.asList(appointment1, appointment2, appointment3, appointment4));

        when(appointmentRepository.findAll()).thenReturn(appointments);
        EnumMap<Specialty, HashSet<Patient>> patientsByDepartment = groupingService.findPatientsByDepartment();

        assertEquals(2, patientsByDepartment.get(Specialty.CARDIOLOGY).size());
        assertEquals(1, patientsByDepartment.get(Specialty.DERMATOLOGY).size());
    }

    @Test
    public void testFindPatientsCountByDepartment() {
        doctor1.setSpecialty(Specialty.CARDIOLOGY.name());
        doctor2.setSpecialty(Specialty.CARDIOLOGY.name());
        doctor3.setSpecialty(Specialty.DERMATOLOGY.name());
        appointment1.setPatient(patient1);
        appointment2.setPatient(patient2);
        appointment3.setPatient(patient1);
        appointment4.setPatient(patient1);
        appointment1.setDoctor(doctor1);
        appointment2.setDoctor(doctor1);
        appointment3.setDoctor(doctor2);
        appointment4.setDoctor(doctor3);
        List<Appointment> appointments = new ArrayList<>(Arrays.asList(appointment1, appointment2, appointment3, appointment4));

        when(appointmentRepository.findAll()).thenReturn(appointments);
        Map<String, Integer> patientsCountByDepartment = groupingService.findPatientsCountByDepartment();

        assertEquals(2, patientsCountByDepartment.get("Cardiology"));
    }

    @Test
    public void testFindPatientsByDoctor() {
        doctor1.setSpecialty(Specialty.CARDIOLOGY.name());
        doctor2.setSpecialty(Specialty.CARDIOLOGY.name());
        doctor3.setSpecialty(Specialty.DERMATOLOGY.name());
        doctor1.setId(1L);
        doctor2.setId(2L);
        doctor3.setId(3L);
        appointment1.setPatient(patient1);
        appointment2.setPatient(patient2);
        appointment3.setPatient(patient1);
        appointment4.setPatient(patient1);
        appointment1.setDoctor(doctor1);
        appointment2.setDoctor(doctor1);
        appointment3.setDoctor(doctor2);
        appointment4.setDoctor(doctor3);
        List<Appointment> appointments = new ArrayList<>(Arrays.asList(appointment1, appointment2, appointment3, appointment4));

        when(appointmentRepository.findAll()).thenReturn(appointments);
        Set<Patient> patients = groupingService.findPatientsByDoctor(1L);

        assertEquals(2, patients.size());
        assertTrue(patients.contains(patient1));
        assertTrue(patients.contains(patient2));
    }

    @Test
    public void testFindPatientsCountByDoctor() {
        doctor1.setSpecialty(Specialty.CARDIOLOGY.name());
        doctor2.setSpecialty(Specialty.CARDIOLOGY.name());
        doctor3.setSpecialty(Specialty.DERMATOLOGY.name());
        doctor1.setId(1L);
        doctor2.setId(2L);
        doctor3.setId(3L);
        appointment1.setPatient(patient1);
        appointment2.setPatient(patient2);
        appointment3.setPatient(patient1);
        appointment4.setPatient(patient1);
        appointment1.setDoctor(doctor1);
        appointment2.setDoctor(doctor1);
        appointment3.setDoctor(doctor2);
        appointment4.setDoctor(doctor3);
        List<Appointment> appointments = new ArrayList<>(Arrays.asList(appointment1, appointment2, appointment3, appointment4));
        List<Doctor> doctors = new ArrayList<>(Arrays.asList(doctor1, doctor2, doctor3));

        when(doctorRepository.findAll()).thenReturn(doctors);
        when(appointmentRepository.findAll()).thenReturn(appointments);

        Map<Doctor, Long> patientCountByDoctor = groupingService.findPatientsCountByDoctor();

        assertEquals(3, patientCountByDoctor.size());
        assertEquals(2L, patientCountByDoctor.get(doctor1));
        assertEquals(1L, patientCountByDoctor.get(doctor2));
    }
}
