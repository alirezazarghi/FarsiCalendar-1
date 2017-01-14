import com.ibm.icu.util.Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ashkanmehrkar on 5/23/16.
 * This class creates the right click pop up menu.
 */
public class PopUpMenu extends JPopupMenu {
    JMenu event;
    JMenu note;
    JMenuItem editEvent;
    JMenuItem addEvent;
    JMenuItem editNote;
    JMenuItem addNote;

    /**
     * You give the main calendar interface and a persian calendar to it.
     * @param calendar the main calendar interface which you want the pop up menu to be shown on it.
     * @param prsCal the persian calendar which you can hava access to its month, date, year and.. .
     */
    public PopUpMenu(final FarsiCalendar calendar, final Calendar prsCal) {
        super();

        event = new JMenu("رویداد");

        event.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        note = new JMenu("یادداشت");

        note.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        editEvent = new JMenuItem("ویرایش رویداد");

        editEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + ".txt";
                EventEditor eventEditor = new EventEditor(filename);
            }
        });

        addEvent = new JMenuItem("اضافه کردن رویداد");

        addEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + ".txt";
                Event tmpEvent = new Event(fileName);
            }
        });

        event.add(addEvent);

        event.add(editEvent);

        editNote = new JMenuItem("ویرایش یادداشت");

        editNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/notes/" + String.valueOf(prsCal.get(Calendar.YEAR)) + "-" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + "-" + "note" + ".txt";
                NoteEditor noteEditor = new NoteEditor(fileName);
            }
        });

        addNote = new JMenuItem("اضافه کردن یادداشت");

        addNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/notes/" + String.valueOf(prsCal.get(Calendar.YEAR)) + "-" + String.valueOf(prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + "-" + "note" + ".txt";

                Note tmpNote = new Note(fileName);
            }
        });

        note.add(addNote);

        note.add(editNote);

        add(event);

        add(note);


        setVisible(true);
    }
}
