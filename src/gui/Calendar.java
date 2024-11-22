package gui;

import model.MyEvent;
import service.MyEventService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Calendar extends JFrame implements ActionListener {
    private JPanel panel;

    private JTextField labelMonday;
    private JTextField labelTuesday;
    private JTextField labelWednesday;
    private JTextField labelThursday;
    private JTextField labelFriday;
    private JTextField labelSaturday;
    private JTextField labelSunday;

    private  List <JTextField> days = List.of(
            labelMonday,
            labelTuesday,
            labelWednesday,
            labelThursday,
            labelFriday,
            labelSaturday,
            labelSunday);

    private JTextArea textAreaMonday;
    private JTextArea textAreaTuesday;
    private JTextArea textAreaWednsday;
    private JTextArea textAreaThursday;
    private JTextArea textAreaFriday;
    private JTextArea textAreaSuturday;
    private JTextArea textAreaSunday;


    private JTextArea[] textAreas={
            textAreaMonday,
            textAreaTuesday,
            textAreaWednsday,
            textAreaThursday,
            textAreaFriday,
            textAreaSuturday,
            textAreaSunday
    };

    private JTextField textFieldMonday;
    private JTextField textFieldTuesday;
    private JTextField textFieldWednsday;
    private JTextField textFieldThurday;
    private JTextField textFieldFriday;
    private JTextField textFieldSuturday;
    private JTextField textFieldSunday;

    private JTextField[] jTextFields={
            textFieldMonday,
            textFieldTuesday,
            textFieldWednsday,
            textFieldThurday,
            textFieldFriday,
            textFieldSuturday,
            textFieldSunday
    };


    private JButton buttonMonday;
    private JButton buttonTuesday;
    private JButton buttonWednsday;
    private JButton buttonThursday;
    private JButton buttonFriday;
    private JButton buttonSuturday;
    private JButton buttonSunday;

    private List<JButton> buttons=List.of(
            buttonMonday,
            buttonTuesday,
            buttonWednsday,
            buttonThursday,
            buttonFriday,
            buttonSuturday,
            buttonSunday
    );


    private JPanel panelMonday;
    private JPanel panelTuesday;
    private JPanel panelWednsday;
    private JPanel panelThursday;
    private JPanel panelFriday;
    private JPanel panelSaturday;
    private JPanel panelSunday;

    private JPanel[]jpanel ={
            panelMonday,
            panelTuesday,
            panelWednsday,
            panelThursday,
            panelFriday,
            panelSaturday,
            panelSunday
    };

    MyEventService myEventService= new MyEventService();
     DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, dd/MM/yy");
//    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);

    public Calendar()  {
        super("Calendar");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10,10,1200,800);
        this.setResizable(false);
        panel.setSize(150,50);
        init();
        this.add(panel);





    }
    public void init() {


        LocalDate previousMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
//                previous(DayOfWeek.MONDAY));
        //Vi får alla event i en list
        List<MyEvent> allEvents = new MyEventService().getAllEvents();
        //Går i loop och sätta färg på dag som är idag
        for (int i = 0; i < days.size(); i++) {
            days.get(i).setText(dateTimeFormatter.format(previousMonday.plusDays(i)));
            //Om är det today då färgas i grön färg.
            if(days.get(i).getText().equals(dateTimeFormatter.format(LocalDate.now()))){
                jpanel[i].setOpaque(true);
                jpanel[i].setBackground(new Color(51, 255, 128 ));

                textAreas[i].setOpaque(true);
                textAreas[i].setBackground(new Color(51, 255, 128 ));

            }
            //Sätter actionlistener på knappen och visa  upp alla eventer enligt date
            buttons.get(i).addActionListener(this::actionPerformed);
            String result="";
            for (MyEvent event : allEvents) {
                if(event.getDate().equals(previousMonday.plusDays(i))){
                    result=result+(event.getEventName()+" \n");
                }
            }
            textAreas[i].setText(result);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        int index = buttons.indexOf(source);
        //Om text är inte tomt då
        if(!jTextFields[index].getText().equals("")) {
            //anropa metod som sparar i filen ny event
            myEventService.addEvent(new MyEvent(jTextFields[index].getText(), LocalDate.parse(days.get(index).getText(),dateTimeFormatter)));
            List<MyEvent> allEvents = new ArrayList<>();
            //Går genom  loop med alla dates från filen  och om  evets.date equels activitet date då lägga till i listan
            for (MyEvent event : myEventService.getAllEvents()) {
                if (event.getDate().equals(LocalDate.parse(days.get(index).getText(),dateTimeFormatter))) {
                    allEvents.add(event);
                }
            }
            String result="";
            for (MyEvent allEvent : allEvents) {
                result=result+(allEvent.getEventName()+" \n");

            }
            textAreas[index].setText(result);
        }else {
            //En dialog fönstret som kommer om man vill lägga till en  tomt event
            EmptyFieldDialog dialog = new EmptyFieldDialog();
            dialog.setBounds(100,100,300,300);
            //            dialog.pack();
            dialog.setVisible(true);
//            System.exit(0);

        }
        jTextFields[index].setText("");


    }


}
