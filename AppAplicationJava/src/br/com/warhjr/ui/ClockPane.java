package br.com.warhjr.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

public class ClockPane extends JPanel {

    private JLabel clock;

    public ClockPane() {
        setLayout(new BorderLayout());
        clock = new JLabel();
        clock.setHorizontalAlignment(JLabel.LEFT);
        clock.setFont(UIManager.getFont("Label.font").deriveFont(12f));
        tickTock();
        add(clock);

        Timer timer = new Timer(12, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tickTock();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }

    public void tickTock() {
    	String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        clock.setText(timeStamp);
    }
}

