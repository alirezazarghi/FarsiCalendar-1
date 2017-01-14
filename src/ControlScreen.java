import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by ashkanmehrkar on 5/19/16.
 * This class is a panel that shows you the solar, lunar and gregorian's month and year the calendar is showing.
 * It also has two JButtons that your can change the showing month with.
 */
public class ControlScreen extends JPanel {
    private String[] solarMonths = {"فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند"};
    private String[] lunarMonths = {"محرم", "صفر" , "ربیع الاول", "ربیع الثانی" , "جمادی الاول", "جمادی الثانی", "رجب" , "شعبان", "رمضان", "شوال", "ذیقعده", "ذیحجه"};
    private String[] months = {"ژانویه", "فوریه", "مارس", "آوریل", "مه", "ژوئن", "ژوئیه", "اوت", "سپتامبر", "اکتبر", "نوامبر", "دسامبر"};
    private JLabel solarLabel;
    private JLabel lunarLabel;
    private JLabel monthLabel;
    private JPanel monthPanel;
    private Calendar prsCal;
    private Calendar arbCal;
    private Calendar gregCal;

    /**
     * Constructs the appropriate controlScreen for the date given in the constructor.
     * @param solarYear the ideal solar year.
     * @param solarMonth the ideal solar month.
     * @param calendar the calendar to which you want to add the controlScreen.
     */
    public ControlScreen(int solarYear, int solarMonth, final FarsiCalendar calendar){
        super();

        BorderLayout upPanLayout = new BorderLayout();

        setLayout(upPanLayout);

        prsCal = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));

        prsCal.set(solarYear, solarMonth, 1);

        arbCal = Calendar.getInstance(new ULocale("ar_SA"));

        arbCal = solarToLunar(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), prsCal.get(Calendar.DATE));

        gregCal = Calendar.getInstance();

        gregCal = solarToGreg(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), prsCal.get(Calendar.DATE));

        final JButton lastMonth = new JButton();

        lastMonth.setText("ماه قبل");

        lastMonth.setHorizontalTextPosition(JButton.RIGHT);

        lastMonth.setIcon(new ImageIcon(getClass().getResource("back.png")));

        lastMonth.setFont(new Font("X Homa", Font.BOLD, 12));

        lastMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prsCal.set(Calendar.MONTH, prsCal.get(Calendar.MONTH ) - 1);

                arbCal.set(Calendar.MONTH , getLastMonth(arbCal.get(Calendar.MONTH)));//اینجا تابع set برای arbCal درست کار نمیکنه!!

                gregCal.set(Calendar.MONTH, gregCal.get(Calendar.MONTH) - 1);

                DateScreen newDateScreen = new DateScreen(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), calendar.infoPanel, calendar);

                calendar.calendarRepaint(newDateScreen, ControlScreen.this);

                solarLabel.setText(solarMonths[prsCal.get(Calendar.MONTH)] + "   " + prsCal.get(Calendar.YEAR));

                lunarLabel.setText(lunarMonths[arbCal.get(Calendar.MONTH)] + " - " + lunarMonths[getNextMonth(arbCal.get(Calendar.MONTH))] + "   " + arbCal.get(Calendar.YEAR));

                monthLabel.setText(months[gregCal.get(Calendar.MONTH)] + " - " + months[getNextMonth(gregCal.get(Calendar.MONTH))] + "   " + (gregCal.get(Calendar.YEAR)));

            }
        });
        monthPanel = new JPanel();

        monthPanel.setLayout(new BorderLayout());

        solarLabel = new JLabel();

        lunarLabel = new JLabel();

        monthLabel = new JLabel();

        solarLabel.setFont(new Font("X Homa", Font.BOLD, 16));

        lunarLabel.setFont(new Font("X Homa", Font.BOLD, 12));

        monthLabel.setFont(new Font("X Homa", Font.BOLD, 12));

        solarLabel.setText(solarMonths[prsCal.get(Calendar.MONTH)] + "   " + prsCal.get(Calendar.YEAR));

        lunarLabel.setText(lunarMonths[arbCal.get(Calendar.MONTH)] + " - " + lunarMonths[getNextMonth(arbCal.get(Calendar.MONTH))] + "   " + arbCal.get(Calendar.YEAR));

        monthLabel.setText(months[gregCal.get(Calendar.MONTH)] + " - " + months[getNextMonth(gregCal.get(Calendar.MONTH))] + "   " +  (gregCal.get(Calendar.YEAR)));

        lunarLabel.setHorizontalAlignment(JLabel.CENTER);

        solarLabel.setHorizontalAlignment(JLabel.CENTER);

        monthLabel.setHorizontalAlignment(JLabel.CENTER);

        monthPanel.add(solarLabel, BorderLayout.CENTER);

        monthPanel.add(lunarLabel, BorderLayout.SOUTH);

        monthPanel.add(monthLabel, BorderLayout.NORTH);

        monthPanel.add(lastMonth, BorderLayout.WEST);

        add(monthPanel, BorderLayout.SOUTH);

        JButton nextMonth = new JButton();

        nextMonth.setIcon(new ImageIcon(getClass().getResource("next.png")));

        nextMonth.setFont(new Font("X Homa", Font.BOLD, 12));

        nextMonth.setText("ماه بعد");

        nextMonth.setHorizontalTextPosition(JButton.LEFT);

        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                prsCal.set(Calendar.MONTH, prsCal.get(Calendar.MONTH ) + 1);

                arbCal.set(Calendar.MONTH, arbCal.get(Calendar.MONTH) + 1);

                gregCal.set(Calendar.MONTH, gregCal.get(Calendar.MONTH) + 1);

                DateScreen newDateScreen = new DateScreen(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), calendar.infoPanel, calendar);

                calendar.calendarRepaint(newDateScreen, ControlScreen.this);

                solarLabel.setText(solarMonths[prsCal.get(Calendar.MONTH)] + "   " + prsCal.get(Calendar.YEAR));

                lunarLabel.setText(lunarMonths[arbCal.get(Calendar.MONTH)] + " - " + lunarMonths[getNextMonth(arbCal.get(Calendar.MONTH))] + "   " + arbCal.get(Calendar.YEAR));

                monthLabel.setText(months[gregCal.get(Calendar.MONTH)] + " - " + months[getNextMonth(gregCal.get(Calendar.MONTH))] + "   " +  (gregCal.get(Calendar.YEAR)));

            }
        });

        monthPanel.add(nextMonth, BorderLayout.EAST);

        CalendarToolbar calendarToolbar = new CalendarToolbar(solarYear, solarMonth, calendar);

        add(calendarToolbar, BorderLayout.CENTER);

        CalendarMenu calendarMenu = new CalendarMenu(calendar);

        add(calendarMenu, BorderLayout.NORTH);
    }
    /**
     * You give the month's number to this method and it returns the former month.
     * @param a the month's number you give to the method.
     * @return returns the former month's number.
     */
    public int getLastMonth(int a) {
        if(a - 1 <0)
            return (a-1+12);
        else
            return (a -1);
    }
    /**
     * You give the month's number to this method and it returns the next month.
     * @param a the month's number you give to the method.
     * @return returns the next month's number.
     */
    public int getNextMonth(int a) {
        if(a + 1 >= 12)
            return (a+1-12);
        else
            return (a+1);
    }
    /**
     * This method changes a solar date to a lunar date.
     * @param solarYear your ideal solar year.
     * @param solarMonth your ideal solar month.
     * @param solarDate your ideal solar date.
     * @return returns a new lunar calendar which is set by the date given.
     */
    public Calendar solarToLunar(int solarYear, int solarMonth, int solarDate) {
        Calendar cal = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));
        cal.set(solarYear, solarMonth, solarDate);
        Date date1 = cal.getTime();
        Calendar arbCal = Calendar.getInstance(new ULocale("ar_SA"));
        arbCal.setTime(date1);
        return arbCal;
    }
    /**
     * This method changes a solar date to a gregorian date.
     * @param year your ideal solar year.
     * @param month your ideal solar month.
     * @param date your ideal solar date.
     * @return returns a new gregorian calendar which is set by the date given.
     */
    public Calendar solarToGreg(int year, int month, int date) {
        Calendar cal = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));
        cal.set(year, month, date);
        Date a = cal.getTime();
        Calendar gregCal = Calendar.getInstance();
        gregCal.setTime(a);
        return gregCal;
    }

}
