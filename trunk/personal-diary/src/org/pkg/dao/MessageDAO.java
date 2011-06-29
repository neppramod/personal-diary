package org.pkg.dao;

import java.util.Date;
import java.util.List;

import org.pkg.domain.Message;

public interface MessageDAO {

    public void addMessage(Message message);

    public List<Message> listMessage();

    public void removeMessage(Integer id);

    public List<Message> listMessage(Date date);

    public List<Message> listMessage(String query);

	public Message get(int id);
}
