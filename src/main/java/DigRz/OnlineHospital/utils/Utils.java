package DigRz.OnlineHospital.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class Utils {

    public List<LocalDate> generateListOfDays() {
        LocalDate startDay = LocalDate.now().plusDays(15);
        List<LocalDate> daysList = new ArrayList<>();
        for (int i = 0; i < 14; i++) {     //за период от две седмици, начална дата - 15 дни напред от текущата
            daysList.add(startDay.plusDays(i));
        }
        return daysList;
    }

    public List generateListOfHours() {
        LocalTime startHour = LocalTime.of(9,0);
        List<LocalTime> hoursList = new ArrayList<>();
        for (int i = 0; i < 18; i++) {     //за девет часа от 9:00 през 30 минути с обедна почивка между 12:00 и 13:00
            if (i<6 || i>7) hoursList.add(startHour.plusMinutes(30*i));
        }
        return hoursList;
    }

}
