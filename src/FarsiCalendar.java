import javax.swing.*;
import java.awt.*;
import com.ibm.icu.util.*;
import com.ibm.icu.util.Calendar;
import com.apple.eawt.Application;

/**
 * Created by ashkanmehrkar on 5/19/16.
 * This class just creates a calendar interface that includes a controlScreen, dateScreen and an infoScreen, toolbar and a menu
 */
public class FarsiCalendar extends JFrame {
    public DateScreen dateScreen;
    public ControlScreen controlScreen;
    public InfoPanel infoPanel;
    public Calendar prsCal;

    public FarsiCalendar() {

        super("تقویم پارسی");

        setLayout(new BorderLayout());

        Application application = Application.getApplication();

        Image image = Toolkit.getDefaultToolkit().getImage("/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/src/calendar.png");

        application.setDockIconImage(image);

        prsCal = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));

        infoPanel = new InfoPanel(this);

        add(infoPanel, BorderLayout.SOUTH);

        dateScreen = new DateScreen(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), infoPanel, this);

        add(dateScreen, BorderLayout.CENTER);

        controlScreen = new ControlScreen(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), this);

        add(controlScreen, BorderLayout.NORTH);

        SystemTrayDemo systemTray = new SystemTrayDemo(this);



    }

    /**
     * This method removes dateScreen and controlScreen of the calendar and repaint it with the objects given.
     * @param newDateScreen the dateScreen that is created newly.
     * @param newControlScreen the controlScreen that is created newly.
     */
    public void calendarRepaint(DateScreen newDateScreen, ControlScreen newControlScreen) {

        remove(dateScreen);

        remove(controlScreen);

        add(newDateScreen, BorderLayout.CENTER);

        add(newControlScreen, BorderLayout.NORTH);

        dateScreen = newDateScreen;

        controlScreen = newControlScreen;

        revalidate();

        repaint();

    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        FarsiCalendar calendar = new FarsiCalendar();

        calendar.setVisible(true);

        calendar.setSize(400, 700);

        //calendar.pack();

        calendar.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        calendar.dateScreen.datePanelArray[calendar.dateScreen.firstDayOfSolarMonth + 6 + calendar.prsCal.get(Calendar.DATE)].setBackground(Color.ORANGE);

    }

}
