import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by ashkanmehrkar on 5/18/16.
 * This class includes JButtons on it and is a simple toolbar.
 */
public class CalendarToolbar extends JToolBar {
    private JButton nextMonth;
    private JButton lastMonth;
    private JButton addEvent;
    private JButton addNote;
    private Calendar prsCal;
    private Calendar arbCal;
    private Calendar gregCal;
    private JButton editEvent;
    private JButton editNote;
    private JButton timer;

    /**
     * In this constructor you give the year, month and the calendar interface and it creates the appropriate toolbar.
     * @param solarYear the solarYear that you want the calendar to show.
     * @param solarMonth the solarMonth that you want the calendar to show.
     * @param calendar the main calendar interface to which you want the toolbar to add.
     */
    public CalendarToolbar(int solarYear, int solarMonth, final FarsiCalendar calendar) {
        super();

        nextMonth = new JButton();

        nextMonth.setIcon(new ImageIcon(getClass().getResource("next1.png")));

        nextMonth.setToolTipText("ماه بعد");

        lastMonth = new JButton();

        lastMonth.setIcon(new ImageIcon(getClass().getResource("back1.png")));

        lastMonth.setToolTipText("ماه قبل");

        addEvent = new JButton();

        addEvent.setToolTipText("تعریف رویداد");

        addEvent.setIcon(new ImageIcon(getClass().getResource("addEvent.png")));

        addNote = new JButton();

        addNote.setToolTipText("تعریف یادداشت");

        addNote.setIcon(new ImageIcon(getClass().getResource("addNote.png")));

        editEvent = new JButton();

        editEvent.setToolTipText("ویرایش رویداد");

        editEvent.setIcon(new ImageIcon(getClass().getResource("editEvent.png")));

        editNote = new JButton();

        editNote.setToolTipText("ویرایش یادداشت");

        editNote.setIcon(new ImageIcon(getClass().getResource("editNote.png")));


        prsCal = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));
        prsCal.set(solarYear, solarMonth, 1);

        arbCal = Calendar.getInstance(new ULocale("ar_SA"));
        arbCal = solarToLunar(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), prsCal.get(Calendar.DATE));

        gregCal = Calendar.getInstance();
        gregCal = solarToGreg(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), prsCal.get(Calendar.DATE));

        lastMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prsCal.set(Calendar.MONTH, prsCal.get(Calendar.MONTH ) - 1);

                arbCal.set(Calendar.MONTH, getLastMonth(arbCal.get(Calendar.MONTH)));

                gregCal.set(Calendar.MONTH, gregCal.get(Calendar.MONTH) - 1);

                DateScreen newDateScreen = new DateScreen(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), calendar.infoPanel, calendar);

                ControlScreen newControlScreen = new ControlScreen(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), calendar);

                calendar.calendarRepaint(newDateScreen, newControlScreen);

            }
        });
        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prsCal.set(Calendar.MONTH, prsCal.get(Calendar.MONTH ) + 1);

                arbCal.set(Calendar.MONTH, arbCal.get(Calendar.MONTH) + 1);

                gregCal.set(Calendar.MONTH, gregCal.get(Calendar.MONTH) + 1);

                DateScreen newDateScreen = new DateScreen(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), calendar.infoPanel, calendar);
                ControlScreen newControlScreen = new ControlScreen(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), calendar);
                calendar.calendarRepaint(newDateScreen, newControlScreen);

            }
        });

        addNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/notes/" + String.valueOf(prsCal.get(Calendar.YEAR)) + "-" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + "-" + "note" + ".txt";
                Note tmpNote = new Note(fileName);
            }
        });

        addEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + ".txt";
                Event tmpEvent = new Event(fileName);
            }
        });

        editNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/notes/" + String.valueOf(prsCal.get(Calendar.YEAR)) + "-" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + "-" + "note" + ".txt";
                NoteEditor noteEditor = new NoteEditor(fileName);
            }
        });

        editEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + ".txt";
                EventEditor eventEditor = new EventEditor(filename);
            }
        });

        timer = new JButton();

        timer.setToolTipText("تایمر");

        timer.setIcon(new ImageIcon(getClass().getResource("alarm-clock.png")));

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimerDemo timerDemo = new TimerDemo();
            }
        });

        setRollover(true);

        addSeparator();

        add(lastMonth);

        add(nextMonth);

        add(addEvent);

        add(editEvent);

        add(addNote);

        add(editNote);

        add(timer);

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

}
