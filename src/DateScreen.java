import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

/**
 * Created by ashkanmehrkar on 5/19/16.
 * This class constructs the appropriate dateScreen, for the month and year given to it.
 */
public class DateScreen extends JPanel {

    private String[] dates = {"", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹", "۱۰", "۱۱", "۱۲", "۱۳", "۱۴", "۱۵", "۱۶", "۱۷", "۱۸", "۱۹", "۲۰", "۲۱", "۲۲", "۲۳", "۲۴", "۲۵", "۲۶", "۲۷", "۲۸", "۲۹", "۳۰", "۳۱"};
    private String[] days = {"شنبه", "يکشنبه", "دوشنبه", "سه شنبه", "چهارشنبه", "پنجشنبه", "جمعه"};
    private JLabel[] solarDateLabel;
    private JLabel[] dayLabel;
    public JPanel[] datePanelArray;
    private JLabel[] lunarDateLabel;
    private JLabel[] dateLabel;
    private Calendar arbCal;
    private Calendar prsCal;
    private Calendar gregCal;
    public int firstDayOfSolarMonth;
    private int clickedDatePanel;

    /**
     * You give the year and month to it then it automatically calculates the first day of month and stick the JLabels to all panels.
     * You should give it the infoPanel which is added to the FarsiCalendar.
     * @param solarYear the ideal year you want the datePanel to show.
     * @param solarMonth the ideal month you want the datePanel to show.
     * @param inf the infoPanel which is added to the farsiCalendar.
     * @param calendar the main calendar interface.
     */
    public DateScreen(int solarYear, int solarMonth, final InfoPanel inf, final FarsiCalendar calendar) {

        super();

        setLayout(new GridLayout(7, 7, 3, 3));

        setOpaque(false);

        setVisible(true);

        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        datePanelArray = new JPanel[49];

        solarDateLabel = new JLabel[42];

        dayLabel = new JLabel[7];

        lunarDateLabel = new JLabel[42];

        dateLabel = new JLabel[42];


        for (int i = 0; i < 49; i++) {

            datePanelArray[i] = new JPanel();

            datePanelArray[i].setLayout(new BorderLayout());

            datePanelArray[i].setBorder(BorderFactory.createEtchedBorder());

            datePanelArray[i].setVisible(false);

        }

        for (int i = 7; i < 49; i++)

            datePanelArray[i].addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    if(e.isMetaDown()) {
                        PopUpMenu menu = new PopUpMenu(calendar, prsCal);
                        menu.show(e.getComponent(), e.getX(), e.getY());
                    }
                    String date = null;

                    for (int i = 7; i < 49; i++) {

                        datePanelArray[i].setBackground(new Color(160, 160, 160));

                        if (e.getSource() == datePanelArray[i]) {

                            clickedDatePanel = i;

                            datePanelArray[i].setBackground(Color.ORANGE);

                            date = solarDateLabel[i - 7].getText();

                            for(int j = 1; j < 32; j++) {

                                if(dates[j].equals(date))

                                    date = String.valueOf(j);

                            }
                        }
                    }

                    String eventFileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + date + ".txt";

                    File eventFile = new File(eventFileName);

                    byte[] eventData = new byte[(int) eventFile.length()];

                    FileInputStream eventFis = null;

                    try {
                        eventFis = new FileInputStream(eventFile);
                    } catch (FileNotFoundException a) {
                        a.printStackTrace();
                    }

                    try {
                        eventFis.read(eventData);
                        eventFis.close();
                    } catch (IOException b) {
                        b.printStackTrace();
                    }


                    try {
                        String z = new String(eventData, "UTF-8");
                        inf.events.setText(z);
                    } catch (UnsupportedEncodingException c) {
                        c.printStackTrace();
                    }

                    String noteFileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/notes/" + String.valueOf(prsCal.get(Calendar.YEAR)) + "-" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(getClickedDate()) + "-" + "note" + ".txt";

                    File noteFile = new File(noteFileName);

                    byte[] noteData = new byte[(int) noteFile.length()];

                    FileInputStream noteFis = null;

                    try {
                        noteFis = new FileInputStream(noteFile);
                    } catch (FileNotFoundException a) {
                        a.printStackTrace();
                    }

                    try {
                        noteFis.read(noteData);
                        noteFis.close();
                    } catch (IOException b) {
                        b.printStackTrace();
                    }


                    try {
                        String z = new String(noteData, "UTF-8");
                        inf.notes.setText(z);
                    } catch (UnsupportedEncodingException c) {
                        c.printStackTrace();
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                    String date = null;

                    for (int i = 7; i < 49; i++) {

                        if (e.getSource() == datePanelArray[i]) {

                            date = solarDateLabel[i - 7].getText();

                            for(int j = 1; j < 32; j++) {

                                if(dates[j].equals(date))

                                    date = String.valueOf(j);
                            }
                            break;
                        }
                    }

                    String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + date + ".txt";

                    File file1 = new File(fileName);

                    byte[] data1 = new byte[(int) file1.length()];

                    FileInputStream fis1 = null;

                    try {
                        fis1 = new FileInputStream(file1);
                    } catch (FileNotFoundException ev) {
                        ev.printStackTrace();
                    }

                    try {
                        fis1.read(data1);
                        fis1.close();
                    } catch (IOException ev1) {
                        ev1.printStackTrace();
                    }

                    String z = null;
                    try {
                        z = new String(data1, "UTF-8");
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }

                    String noteFileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/notes/" + String.valueOf(prsCal.get(Calendar.YEAR)) + "-" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + date + "-" + "note" + ".txt";

                    File file = new File(noteFileName);

                    byte[] data = new byte[(int) file.length()];

                    FileInputStream fis = null;

                    try {
                        fis = new FileInputStream(file);
                    } catch (FileNotFoundException ev) {
                        ev.printStackTrace();
                    }

                    try {
                        fis.read(data);
                        fis.close();
                    } catch (IOException ev1) {
                        ev1.printStackTrace();
                    }


                    try {
                        String j = new String(data, "UTF-8");
                        String result = z + "   " + j;
                        for(int i = 7; i < 49; i++) {

                            if(e.getSource() == datePanelArray[i])

                                datePanelArray[i].setToolTipText(result);
                        }
                    } catch (UnsupportedEncodingException ev2) {
                        ev2.printStackTrace();
                    }

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

            });

        for(int i = 7; i < 49; i++)
            datePanelArray[i].setBackground(new Color(160, 160, 160));
/**
 * Here we set day panels at top of the date panel
 */
        for (int i = 0; i < 7; i++) {

            datePanelArray[i].setVisible(true);

            dayLabel[i] = new JLabel();

            dayLabel[i].setFont(new Font("X Homa", Font.BOLD, 16));

            datePanelArray[i].setBackground(new Color(75, 148, 140));

            dayLabel[i].setText(days[i]);

            dayLabel[i].setHorizontalAlignment(JLabel.CENTER);

            datePanelArray[i].add(dayLabel[i], BorderLayout.CENTER);

            if(i%6 == 0 && i != 0) {

                dayLabel[i].setForeground(Color.RED);

            }
        }

/**
 * Here we create the solar and lunar labels and add the to the date panel
 */
        for (int i = 0; i < 42; i++) {

            solarDateLabel[i] = new JLabel();


            lunarDateLabel[i] = new JLabel();

            dateLabel[i] = new JLabel();

            datePanelArray[i + 7].add(solarDateLabel[i], BorderLayout.CENTER);

            datePanelArray[i + 7].add(lunarDateLabel[i], BorderLayout.SOUTH);

            datePanelArray[i + 7].add(dateLabel[i], BorderLayout.NORTH);

            if(i%7 == 6 && i != 0) {

                solarDateLabel[i].setForeground(Color.RED);

            }
        }

        prsCal = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));

        prsCal.set(solarYear, solarMonth, 1);

        arbCal = Calendar.getInstance(new ULocale("ar_SA"));

        arbCal = solarToLunar(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), prsCal.get(Calendar.DATE));

        gregCal = Calendar.getInstance();

        gregCal = solarToGreg(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), prsCal.get(Calendar.DATE));

        firstDayOfSolarMonth = prsCal.get(Calendar.DAY_OF_WEEK);

        if (firstDayOfSolarMonth == 7)

            firstDayOfSolarMonth = firstDayOfSolarMonth - 7;


        int a = firstDayOfSolarMonth + prsCal.getActualMaximum(Calendar.DATE);

        for (int i = firstDayOfSolarMonth; i < a; i++) {

            datePanelArray[i + 7].setVisible(true);

            solarDateLabel[i].setFont(new Font("X Homa", Font.BOLD, 16));

            solarDateLabel[i].setText(dates[prsCal.get(Calendar.DATE)]);

            solarDateLabel[i].setHorizontalAlignment(JLabel.CENTER);

            String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(prsCal.get(Calendar.DATE)) + ".txt";

            File file = new File(fileName);

            try {

                file.createNewFile();

            } catch (IOException e) {

                e.printStackTrace();

            }

            String noteFileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/notes/" + String.valueOf(prsCal.get(Calendar.YEAR)) + "-" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(prsCal.get(Calendar.DATE)) + "-" + "note" + ".txt";

            File file1 = new File(noteFileName);

            try {

                file1.createNewFile();

            } catch (IOException e) {

                e.printStackTrace();

            }

            prsCal.set(Calendar.DATE, prsCal.get(Calendar.DATE) + 1);
        }

        prsCal.set(Calendar.MONTH, prsCal.get(Calendar.MONTH) - 1);

        for (int i = firstDayOfSolarMonth; i < a; i++) {

            lunarDateLabel[i].setFont(new Font("X Homa", Font.PLAIN, 12));

            lunarDateLabel[i].setText(dates[arbCal.get(Calendar.DATE)]);

            lunarDateLabel[i].setHorizontalAlignment(JLabel.RIGHT);

            arbCal.set(Calendar.DATE, arbCal.get(Calendar.DATE) + 1);
        }

        for (int i = firstDayOfSolarMonth; i < a; i++) {

            dateLabel[i].setFont(new Font("X Homa", Font.PLAIN, 12));

            dateLabel[i].setText(String.valueOf(gregCal.get(Calendar.DATE)));

            gregCal.set(Calendar.DATE, gregCal.get(Calendar.DATE) + 1);
        }

        for (int i = 0; i < 49; i++) {

            add(datePanelArray[i]);
        }
    }

    /**
     * This method changes a solar date to a lunar date.
     * @param solarYear your ideal solar year.
     * @param solarMonth your ideal solar month.
     * @param solarDate your ideal solar date.
     * @return returns a new lunar calendar which is set by the date given.
     */
    private Calendar solarToLunar(int solarYear, int solarMonth, int solarDate) {

        Calendar cal = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));

        cal.set(solarYear, solarMonth, solarDate);

        Calendar arbCal = Calendar.getInstance(new ULocale("ar_SA"));

        arbCal.setTime(cal.getTime());

        return arbCal;
    }
    /**
     * This method changes a solar date to a gregorian date.
     * @param year your ideal solar year.
     * @param month your ideal solar month.
     * @param date your ideal solar date.
     * @return returns a new gregorian calendar which is set by the date given.
     */
    private Calendar solarToGreg(int year, int month, int date) {

        Calendar cal = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));

        cal.set(year, month, date);

        Calendar gregCal = Calendar.getInstance();

        gregCal.setTime(cal.getTime());

        return gregCal;
    }

    /**
     * This method returns date, which you've clicked on it.
     * @return the date which you've clicked on it.
     */
    public int getClickedDate() {

        try{
            for(int i = 0; i < 32; i++) {

                if(solarDateLabel[clickedDatePanel - 7].getText().equals(dates[i]))

                    return i;

            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return 1;
        }
        return 1;

    }

}
