import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ashkanmehrkar on 5/24/16.
 * This class opens a new JFrame that is similar to a timer where you can enter the ideal hour and minute for alarm.
 */
public class TimerDemo extends JFrame {
    private JLabel txt;
    private JTextField hour;
    private JTextField minute;
    private JPanel pan1;
    private JPanel pan;
    private JButton ok;
    private JButton cancel;

    /**
     * Opens a new JFrame where you can enter ideal hour and minute for alarm.
     */
    public TimerDemo() {

        super("تایمر");

        setLayout(new BorderLayout());

        txt = new JLabel();

        txt.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        txt.setText("زمان را وارد کنید:");

        add(txt, BorderLayout.NORTH);

        pan1 = new JPanel();

        pan1.setLayout(new FlowLayout());

        hour = new JTextField("ساعت");

        minute = new JTextField("دقیقه");

        pan1.add(hour);

        pan1.add(minute);

        add(pan1, BorderLayout.CENTER);

        pan = new JPanel();

        pan.setLayout(new FlowLayout());

        ok = new JButton("تایید");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hour1 = Integer.parseInt(hour.getText());

                int minute1 = Integer.parseInt(minute.getText());

                JTmerTask jTmerTask = new JTmerTask(hour1, minute1, "Your Time Is Up! :D");

                dispose();
            }
        });

        cancel = new JButton("انصراف");

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pan.add(ok);

        pan.add(cancel);

        add(pan, BorderLayout.SOUTH);

        setVisible(true);

        setSize(200, 200);
    }
}
