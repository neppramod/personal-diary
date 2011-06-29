package org.pkg.service;

import java.util.Date;
import java.util.List;

import org.pkg.dao.MessageDAO;
import org.pkg.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Transactional
    public void addMessage(Message message) {
        messageDAO.addMessage(message);
    }

    @Transactional
    public List<Message> listMessage() {
        return messageDAO.listMessage();
    }

    @Transactional
    public void removeMessage(Integer id) {
        messageDAO.removeMessage(id);
    }

    @Transactional
    public List<Message> listMessage(Date date) {
        return messageDAO.listMessage(date);
    }

    @Transactional
    public List<Message> listMessageByTitle(String query) {
        return messageDAO.listMessage(query.toLowerCase());
    }

	@Transactional
	public Message getMessage(int id) {
		return messageDAO.get(id);
	}
}
