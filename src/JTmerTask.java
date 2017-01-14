import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 * Created by ashkanmehrkar on 5/22/16.
 * This class is similar to a real timer, which takes the hour and minute and the ideal string to show at the appropriate time!
 */
public class JTmerTask {

    Timer timer;
    String title;
    int hour;
    int minute;
    int day;
    int month;

    /**
     * Here you should give the ideal hour, minute and title which you want to be shown at the appropriate time.
     * @param hour ideal hour.
     * @param minute ideal minute.
     * @param title ideal title for your alarm.
     */
    public JTmerTask(int hour, int minute,String title) {

        this.title = title;

        this.hour = hour;

        this.minute = minute;

        timer = new Timer();

        Calendar calendar = Calendar.getInstance();

        long now = calendar.getTimeInMillis();

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),

                calendar.get(Calendar.DAY_OF_MONTH), hour, minute, 0);

        long then = calendar.getTimeInMillis();

        if (then - now > 0) {

            timer.schedule(new myReminder(), then - now);

        }

        else{
//
//      JOptionPane.showMessageDialog(null, "Am I kidding?", "Who dares play with me?", JOptionPane.ERROR_MESSAGE);

        }

    }

    class myReminder extends TimerTask {

        public void run() {

            System.out.format("Timer Task Finished..!%n");

            JOptionPane.showMessageDialog(null, "زمانت تموم شده!", "اخطار!!!", JOptionPane.WARNING_MESSAGE);

            timer.cancel();
        }
    }

}