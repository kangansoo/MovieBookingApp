package com.kosa.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws java.text.ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws java.text.ParseException {
        if (value instanceof Date) {
            return dateFormatter.format((Date) value);
        } else if (value instanceof java.util.Calendar) {
            return dateFormatter.format(((java.util.Calendar) value).getTime());
        }
        return "";
    }

}
