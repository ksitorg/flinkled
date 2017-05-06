package de.haw.eled.model;

import javax.mail.Address;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Tim on 19.04.2017.
 */
public interface MailMessage extends Serializable {
    String getSubject();
    Address[] getSender();
    Address[] getReceiver();
    String getMessage();

}
