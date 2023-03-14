package DigRz.OnlineHospital.unit;

import DigRz.OnlineHospital.utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UtilsTest {

    @Mock
    private LocalDate localDate;

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
