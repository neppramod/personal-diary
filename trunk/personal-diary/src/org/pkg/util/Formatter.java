package org.pkg.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pkg.domain.Message;
import org.pkg.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Formats message according to limits given at messages.properties
 * 
 * @author pramod
 * 
 */
public class Formatter {

	@Autowired
	private MessageService messageService;

	/**
	 * Utiity Function to format title and description size that we prepare to
	 * display on list table. It works with no page also If it was default list
	 * page then pagination would go for all list If it is for save, pagination
	 * would go for list with specific date
	 * 
	 * @param titleSize
	 *            length of title text
	 * @param descriptionSize
	 *            length of description text
	 * @param start
	 *            start index
	 * @param limit
	 *            no. of entries to display
	 * @param dateString
	 *            date for list
	 * @return list of formatted message
	 */

	public List<Message> formattedMessageList(int titleSize,
			int descriptionSize, int start, int limit, Date date) {
		List<Message> messages = new ArrayList<Message>();
		List<Message> messageList;

		if (date != null)
			messageList = messageService.listMessage(date);
		else
			messageList = messageService.listMessage();

		if (limit > messageList.size())
			limit = messageList.size();

		// Lets format in descending order
		for (int i = messageList.size() - 1; i >= 0; i--) {
			Message message = messageList.get(i);

			if (message.getTitle().length() > titleSize)
				message.setTitle(message.getTitle().substring(0, titleSize - 1)
						+ " ..");

			if (message.getDescription().length() > descriptionSize)
				message.setDescription(message.getDescription().substring(0,
						descriptionSize - 1)
						+ " ..");

			messages.add(message);
		}

		messages = messages.subList(start, limit);

		return messages;
	}

	/**
	 * Utiity Function to format title and description size that we prepare to
	 * display on list table.Here we format according to search query
	 * 
	 * @param titleSize
	 *            length of title text
	 * @param descriptionSize
	 *            length of description text
	 * @param start
	 *            start index
	 * @param limit
	 *            no. of entries to display
	 * @param query
	 *            search query
	 * @return list of formatted message
	 */
	public List<Message> formattedMessageList(int titleSize,
			int descriptionSize, int start, int limit, String query) {
		List<Message> messages = new ArrayList<Message>();
		List<Message> messageList;

		/* I hoped it is null, but it is empty string */
		if (!query.equals(""))
			messageList = messageService.listMessageByTitle(query);
		else
			messageList = messageService.listMessage();

		if (limit > messageList.size())
			limit = messageList.size();

		// Lets get the messages
		for (Message message : messageList) {

			if (message.getTitle().length() > titleSize)
				message.setTitle(message.getTitle().substring(0, titleSize - 1)
						+ " ..");

			if (message.getDescription().length() > descriptionSize)
				message.setDescription(message.getDescription().substring(0,
						descriptionSize - 1)
						+ " ..");

			messages.add(message);
		}

		messages = messages.subList(start, limit);

		return messages;
	}
	
	/**
	 * Formats html < (less than) and > (greater than) character to represent
	 * as it is in html
	 * @param input
	 * @return
	 */
	
	/*
	public String formatHTML(String input) {        

        String formattedString = input;

        // Well it is a bit expensive to do two work on immuatable string object.
        // But it's easy 
        formattedString = formattedString.replace("<", "&lt;");
        formattedString = formattedString.replace(">", "&gt;" );

        return formattedString;
    }
    */
}
