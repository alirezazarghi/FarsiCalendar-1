import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by ashkanmehrkar on 5/22/16.
 * This class opens a new Jframe which includes buttons and a panel that you can write anything on it.
 */
public class Event extends JFrame {
    private JToolBar toolBar;
    private JTextArea textArea;
    private JCheckBox bold;
    private JCheckBox italic;
    private JCheckBox strikeThrough;
    private JButton link;
    private JButton attachPhoto;
    private JPanel buttonsPanel;
    private JButton ok;
    private JButton cancel;
    private String note;

    /**
     * This constructor opens a new JFrame and saves the string which you've written on it to the fileName which is given to its constructor.
     * @param fileName the fileName which you want to save the notes in it.
     */
    public Event(final String fileName) {
        super("رویداد");

        setLayout(new BorderLayout());

        toolBar = new JToolBar();

        toolBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        bold = new JCheckBox("درشت کردن");

        italic = new JCheckBox("ایتالیک");

        italic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font font = null; // stores the new Font

                if (bold.isSelected() && italic.isSelected())

                    font = new Font("X Homa", Font.BOLD + Font.ITALIC, 16);

                else if(bold.isSelected())

                    font = new Font("X Homa", Font.BOLD, 16);

                else if(italic.isSelected())

                    font = new Font("X Homa", Font.ITALIC, 14);

                else

                    font = new Font("X Homa", Font.PLAIN, 14);

                textArea.setFont(font);
            }
        });

        bold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font font = null; // stores the new Font

                if (bold.isSelected() && italic.isSelected())

                    font = new Font("X Homa", Font.BOLD + Font.ITALIC, 16);

                else if(bold.isSelected())

                    font = new Font("X Homa", Font.BOLD, 16);

                else if(italic.isSelected())

                    font = new Font("X Homa", Font.ITALIC, 14);

                else

                    font = new Font("X Homa", Font.PLAIN, 14);

                textArea.setFont(font);
            }
        });

        strikeThrough = new JCheckBox("خط زدن");

        strikeThrough.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Font font = null;

                if (bold.isSelected() && italic.isSelected())

                    font = new Font("X Homa", Font.BOLD + Font.ITALIC, 16);

                else if(bold.isSelected())

                    font = new Font("X Homa", Font.BOLD, 16);

                else if(italic.isSelected())

                    font = new Font("X Homa", Font.ITALIC, 14);

                else

                    font = new Font("X Homa", Font.PLAIN, 14);

                textArea.setFont(font);
            }
        });

        link = new JButton("لینک");

        attachPhoto = new JButton("پیوست کردن تصویر");

        toolBar.add(bold);

        toolBar.add(italic);

        toolBar.add(strikeThrough);

        toolBar.add(link);

        toolBar.add(attachPhoto);

        add(toolBar, BorderLayout.NORTH);

        textArea = new JTextArea("اینجا بنویس!");

        textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        textArea.setFont(new Font("X Homa", Font.PLAIN, 14));

        add(textArea, BorderLayout.CENTER);

        buttonsPanel = new JPanel();

        buttonsPanel.setLayout(new FlowLayout());

        buttonsPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        ok = new JButton("تایید");

        cancel = new JButton("انصراف");

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                note = textArea.getText();

                File file = new File(fileName);

                try {

                    file.createNewFile();

                } catch (IOException event) {

                    event.printStackTrace();

                }

                try {

                    FileWriter fileWriter = new FileWriter(file);

                    fileWriter.write(note);

                    fileWriter.flush();

                    fileWriter.close();

                } catch (IOException e1) {

                    e1.printStackTrace();

                }

                dispose();
            }
        });

        buttonsPanel.add(cancel);

        buttonsPanel.add(ok);

        add(buttonsPanel, BorderLayout.SOUTH);

        this.setVisible(true);

        this.setSize(400, 300);

    }
}
