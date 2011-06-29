package org.pkg.service;

import java.util.Date;
import java.util.List;

import org.pkg.domain.Message;

/**
 * Service class for message.
 * It performs service layer for CRUD operation
 * @author pramod
 *
 */
public interface MessageService {
    public void addMessage(Message message);

    public List<Message> listMessage();

    public void removeMessage(Integer id);

    public List<Message> listMessage(Date date);

    public List<Message> listMessageByTitle(String query);

	public Message getMessage(int id);
}
