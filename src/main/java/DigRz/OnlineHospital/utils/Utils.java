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
        for (int i = 0; i < 14; i++) {     //за две седмици
            daysList.add(startDay.plusDays(i));
        }
        return daysList;
    }

    public List generateListOfHours() {
        LocalTime startHour = LocalTime.of(9,0);
        List<LocalTime> hoursList = new ArrayList<>();
        for (int i = 0; i < 16; i++) {     //за осем часа през 30 минути
            hoursList.add(startHour.plusMinutes(30*i));
        }
        return hoursList;
    }

}
