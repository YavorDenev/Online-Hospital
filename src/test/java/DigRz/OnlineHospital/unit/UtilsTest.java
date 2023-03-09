package DigRz.OnlineHospital.unit;

import DigRz.OnlineHospital.utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UtilsTest {

    @Mock
    private LocalDate localDate;

//    @InjectMocks
//    private Utils utils;

//    @Test
//    public void generateListOfDaysTest() {
//        when(LocalDate.now()).thenReturn(LocalDate.of(2023, 3, 9));
//
//        List<LocalDate> expectedList = new ArrayList<>();
//        expectedList.add(LocalDate.of(2023, 3, 24));
//        expectedList.add(LocalDate.of(2023, 3, 25));
//        expectedList.add(LocalDate.of(2023, 3, 26));
//        expectedList.add(LocalDate.of(2023, 3, 27));
//        expectedList.add(LocalDate.of(2023, 3, 28));
//        expectedList.add(LocalDate.of(2023, 3, 29));
//        expectedList.add(LocalDate.of(2023, 3, 30));
//        expectedList.add(LocalDate.of(2023, 3, 31));
//        expectedList.add(LocalDate.of(2023, 4, 1));
//        expectedList.add(LocalDate.of(2023, 4, 2));
//        expectedList.add(LocalDate.of(2023, 4, 3));
//        expectedList.add(LocalDate.of(2023, 4, 4));
//        expectedList.add(LocalDate.of(2023, 4, 5));
//        expectedList.add(LocalDate.of(2023, 4, 6));
//
//        Utils utils = new Utils();
//        List<LocalDate> actualList = utils.generateListOfDays();
//
//        assertEquals(expectedList, actualList);
//    }

    @Test
    public void generateListOfHoursTest() {
        List<LocalTime> expectedList = new ArrayList<>();
        expectedList.add(LocalTime.of(9, 0));
        expectedList.add(LocalTime.of(9, 30));
        expectedList.add(LocalTime.of(10, 0));
        expectedList.add(LocalTime.of(10, 30));
        expectedList.add(LocalTime.of(11, 0));
        expectedList.add(LocalTime.of(11, 30));
        expectedList.add(LocalTime.of(13, 0));
        expectedList.add(LocalTime.of(13, 30));
        expectedList.add(LocalTime.of(14, 0));
        expectedList.add(LocalTime.of(14, 30));
        expectedList.add(LocalTime.of(15, 0));
        expectedList.add(LocalTime.of(15, 30));
        expectedList.add(LocalTime.of(16, 0));
        expectedList.add(LocalTime.of(16, 30));
        expectedList.add(LocalTime.of(17, 0));
        expectedList.add(LocalTime.of(17, 30));

        Utils utils = new Utils();
        List actualList = utils.generateListOfHours();

        assertEquals(expectedList, actualList);
    }
}
