package br.com.installmentmicroservice.utils;

import br.com.installmentmicroservice.models.dto.LoanDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MaturityDateUtil {

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public String maturityDateCalculation(LoanDTO loan, Integer installmentMonth) throws ParseException {

        Date startDate = sdf.parse(loan.getStartDate());

        calendar.setTime(startDate);

        calendar.add(Calendar.MONTH, installmentMonth);

        calendar.set(Calendar.DAY_OF_MONTH, loan.getPaymentDate().getDay());
        verifyIfTheDayIsBusinessDay(calendar);

        int month = calendar.get(Calendar.MONTH) + 1;
        return calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DAY_OF_MONTH);

    }

    public Integer getAmountOfDaysInMonth(Integer month) {
        Calendar date = new GregorianCalendar();
        date.set(Calendar.MONTH, month);
        return date.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public void verifyIfTheDayIsBusinessDay(Calendar calendar) {
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, 2);
        }
        else if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

}
