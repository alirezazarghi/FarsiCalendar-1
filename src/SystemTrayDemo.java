/**
 * Created by ashkanmehrkar on 5/24/16.
 */
import com.ibm.icu.util.Calendar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Created by ashkanmehrkar on 5/24/16.
 * This class makes the calendar to run in background
 * It runs the program in you system tray.
 */
public class SystemTrayDemo {
    /**
     * Here you shoud give the class the main calendar interface.
     * @param calendar the main celander interface which is going to be run in your system tray.
     */
    public SystemTrayDemo(final FarsiCalendar calendar) {

        if (!SystemTray.isSupported()) {

            System.out.println("System tray is not supported !!! ");

            return;
        }

        SystemTray systemTray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().getImage("/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/src/icons/" + String.valueOf(calendar.prsCal.get(Calendar.DATE)) + ".png/");

        PopupMenu trayPopupMenu = new PopupMenu();

        MenuItem action = new MenuItem("نمایش پنجره اصلی");

        action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                calendar.setVisible(true);
            }
        });

        trayPopupMenu.add(action);

        MenuItem mnuAddEvent = new MenuItem("افزودن رویداد");

        mnuAddEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                calendar.setVisible(true);

                String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/" + String.valueOf(calendar.prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + ".txt";

                Event tmpEvent = new Event(fileName);
            }
        });

        trayPopupMenu.add(mnuAddEvent);

        MenuItem mnuAddReminder = new MenuItem("افزودن یادداشت");

        mnuAddReminder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                calendar.setVisible(true);

                String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/notes/" + String.valueOf(calendar.prsCal.get(Calendar.YEAR)) + "-" + String.valueOf(calendar.prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + "-" + "note" + ".txt";

                Note note = new Note(fileName);
            }
        });

        trayPopupMenu.add(mnuAddReminder);

        MenuItem close = new MenuItem("خروج");

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        trayPopupMenu.add(close);

        TrayIcon trayIcon = new TrayIcon(image, "تقویم پارسی", trayPopupMenu);

        trayIcon.setImageAutoSize(true);

        trayIcon.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() >= 2) {
                    calendar.setVisible(true);
                }
            }
        });

        try {

            systemTray.add(trayIcon);

        } catch (AWTException awtException) {

            awtException.printStackTrace();
        }
    }
}
