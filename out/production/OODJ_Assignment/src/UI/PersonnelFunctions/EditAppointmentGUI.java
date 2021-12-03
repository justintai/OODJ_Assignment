package UI.PersonnelFunctions;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class EditAppointmentGUI extends JFrame{
    private JPanel editAppointmentPanel;
    private JButton backButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JCheckBox isDone1CheckBox;
    private JCheckBox isDone2CheckBox;
    private JTextField icTF;
    private JTextArea addressTF;
    private JComboBox stateCB;
    private JComboBox vaccineCodeCB;
    private JDatePickerImpl dose1Date;
    private JTextField passportTF;
    private JTextField nameTF;
    private JTextField telTF;
    private JDatePickerImpl dose2Date;
    private JDatePanelImpl datePanel1;
    private JDatePanelImpl datePanel2;

    public EditAppointmentGUI(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(editAppointmentPanel);
        this.setSize(500, 600);
    }

    // for non-palette component
    private void createUIComponents() {
        UtilDateModel model1 = new UtilDateModel();
        UtilDateModel model2 = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel1 = new JDatePanelImpl(model1, p);
        datePanel2 = new JDatePanelImpl(model2, p);
        dose1Date = new JDatePickerImpl(datePanel1, new EditAppointmentGUI.DateLabelFormatter());
        dose2Date = new JDatePickerImpl(datePanel2, new EditAppointmentGUI.DateLabelFormatter());
    }

    // format date picker date
    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "dd-MM-yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }
    }
}
