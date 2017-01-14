import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ashkanmehrkar on 5/16/16.
 * This class includes a go to today button and two panels that shows the notes and events in selected date.
 */
public class InfoPanel extends JPanel {
    public JTextPane events;
    public JTextPane notes;
    private JButton goToToday;

    /**
     * Constructs a new infoPanel for the given calendar.
     * @param calendar the calendar for which you want to create the infoPanel.
     */
    public InfoPanel(final FarsiCalendar calendar) {
        super();
        setLayout(new GridLayout(3, 1));

        events = new JTextPane();

        notes = new JTextPane();

        events.setFont(new Font("X Homa", Font.BOLD, 14));

        events.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        notes.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        notes.setFont(new Font("X Homa", Font.BOLD, 14));

        events.setBorder(BorderFactory.createEtchedBorder());

        notes.setBorder(BorderFactory.createEtchedBorder());

        events.setEditable(false);

        notes.setEditable(false);

        goToToday = new JButton("امروز");

        goToToday.setFont(new Font("X Homa", Font.BOLD, 14));

        goToToday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Calendar prsCal = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));

                calendar.calendarRepaint(new DateScreen(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), calendar.infoPanel, calendar),new ControlScreen(prsCal.get(Calendar.YEAR), prsCal.get(Calendar.MONTH), calendar));

                calendar.dateScreen.datePanelArray[calendar.dateScreen.firstDayOfSolarMonth + 6 + calendar.prsCal.get(Calendar.DATE)].setBackground(Color.ORANGE);
            }
        });

        add(goToToday);

        add(events);

        add(notes);
    }
}
