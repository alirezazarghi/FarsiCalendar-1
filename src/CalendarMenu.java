import com.ibm.icu.util.Calendar;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.Clipboard;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

/**
 * Created by ashkanmehrkar on 5/18/16.
 * This class creates a menu for our calendar which your can write, edit, copy event or copy date by.
 */
public class CalendarMenu extends JMenuBar {
    private JMenu insert;
    private JMenu edit;
    private JMenuItem addEvent;
    private JMenuItem addNote;
    private JMenuItem  editEvent;
    private JMenuItem editNote;
    private JMenuItem copyDate;
    private JMenuItem copyEvent;

    /**
     * This constructor takes the calendar to which you want to add the calendar menu.
     * @param calendar the calendar to which you want the calendar menu be added.
     */
    public CalendarMenu(final FarsiCalendar calendar) {
        super();
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        insert = new JMenu("درج");
        insert.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        insert.setMnemonic(KeyEvent.VK_I);
        insert.setMnemonic('i');
        add(insert);
        addEvent = new JMenuItem("اضافه کردن رویداد");
        addEvent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        addEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/" + String.valueOf(calendar.prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + ".txt";
                Event tmpEvent = new Event(fileName);
            }
        });
        addNote = new JMenuItem("اضافه کردن یادداشت");
        addNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        addNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/notes/" + String.valueOf(calendar.prsCal.get(Calendar.YEAR)) + "-" + String.valueOf(calendar.prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + "-" + "note" + ".txt";
                Note note = new Note(fileName);
            }
        });
        insert.add(addEvent);
        insert.add(addNote);
        edit = new JMenu("ویرایش");
        edit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        edit.setMnemonic(KeyEvent.VK_E);
        editEvent = new JMenuItem("ویرایش رویداد");
        editEvent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        editEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/" + String.valueOf(calendar.prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + ".txt";
                EventEditor eventEditor = new EventEditor(filename);
            }
        });
        editNote = new JMenuItem("ویرایش یادداشت");
        editNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        editNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/notes/" + String.valueOf(calendar.prsCal.get(Calendar.YEAR)) + "-" + String.valueOf(calendar.prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + "-" + "note" + ".txt";
                NoteEditor noteEditor = new NoteEditor(fileName);
            }
        });
        copyDate = new JMenuItem("کپی کردن تاریخ");
        copyDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        copyDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = String.valueOf(calendar.prsCal.get(Calendar.YEAR)) + "-" + String.valueOf(calendar.prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate());
                StringSelection stringSelection = new StringSelection(str);
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            }
        });
        copyEvent = new JMenuItem("کپی کردن رویداد");
        copyEvent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        copyEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fileName = "/Users/ashkanmehrkar/IdeaProjects/FarsiCalendar/files/" + String.valueOf(calendar.prsCal.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.dateScreen.getClickedDate()) + ".txt";

                File file = new File(fileName);


                byte[] data = new byte[(int) file.length()];

                FileInputStream fis = null;

                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException ev1) {
                    ev1 .printStackTrace();
                }

                try {
                    fis.read(data);
                    fis.close();
                } catch (IOException event) {
                    event.printStackTrace();
                }


                try {
                    String str = new String(data, "UTF-8");
                    StringSelection stringSelection = new StringSelection(str);
                    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clpbrd.setContents(stringSelection, null);
                } catch (UnsupportedEncodingException ev) {
                    ev.printStackTrace();
                }

            }
        });

        edit.add(editEvent);

        edit.add(editNote);

        edit.add(copyDate);

        edit.add(copyEvent);

        add(edit);
    }
}
