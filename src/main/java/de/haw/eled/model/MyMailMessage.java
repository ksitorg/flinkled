package de.haw.eled.model;

import javax.mail.Address;
import java.util.Arrays;

/**
 * Created by Tim on 29.04.2017.
 */
public class MyMailMessage implements MailMessage {

    String subject;
    Address[] sender;
    Address[] receiver;
    String message;

    @Override
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public Address[] getSender() {
        return sender;
    }

    public void setSender(Address[] sender) {
        this.sender = sender;
    }

    @Override
    public Address[] getReceiver() {
        return receiver;
    }

    public void setReceiver(Address[] receiver) {
        this.receiver = receiver;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageAdapter{" +
                "subject=\'" + getSubject() + "\'" +
                ", sender=" + Arrays.toString(getSender()) +
                ", receiver=" + Arrays.toString(getReceiver()) +
                ", message=\'" + getMessage() + "\'" +
                '}';
    }
}
