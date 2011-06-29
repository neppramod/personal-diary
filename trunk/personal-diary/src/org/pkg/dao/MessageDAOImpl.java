package org.pkg.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.pkg.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation.
 * It uses hibernate's sessionFactory to do its work.
 * @author pramod
 *
 */
@Repository
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addMessage(Message message) {
        //sessionFactory.getCurrentSession().save(message);
    	sessionFactory.getCurrentSession().saveOrUpdate(message);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> listMessage() {
        return sessionFactory.getCurrentSession().createQuery("from Message").
                list();
    }

    @Override
    public void removeMessage(Integer id) {
        Message message = (Message) sessionFactory.getCurrentSession().
        	load(Message.class, id);

        if (message != null) {
            sessionFactory.getCurrentSession().delete(message);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> listMessage(Date date) {
        return sessionFactory.getCurrentSession().
        	createQuery("from Message where dateCreated = :date").
        		setParameter("date", date).list();
    }

    @Override
    /**
     * Find by title
     */
    @SuppressWarnings("unchecked")
    public List<Message> listMessage(String query) {
        return sessionFactory.getCurrentSession().
        	createQuery("FROM Message WHERE title LIKE :query").
        	setParameter("query", "%" + query + "%").list();
    }

	@Override	
	public Message get(int id) {
		// Be careful of using load. If error comes at domain properties. It is 
		// definitely place to look for errors
		return (Message)sessionFactory.getCurrentSession().
			get(Message.class, id);
	}
}
