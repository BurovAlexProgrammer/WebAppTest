package ru.hostco.burovalex.webapptest;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

import java.util.Map;

public class FormValidator extends AbstractValidator {

    public void validate(ValidationContext context) {
        Map<String,Property> beanProps = context.getProperties(context.getProperty().getBase());

        validateRoomNumber(context, (Integer)beanProps.get("roomNumber").getValue());
    }

    private void validateRoomNumber(ValidationContext context, int number) {
        if (number <= 0) addInvalidMessage(context, "roomNumber", "Номер кабинета должен быть > 0");
    }
}
