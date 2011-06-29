package org.pkg.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.pkg.domain.Message;
import org.pkg.service.MessageService;
import org.pkg.util.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * It handles all the message CRUD operation. This controller is used to manage
 * the entry of this application
 * 
 * @author pramod
 * 
 */
@Controller
public class BlogController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private Formatter formatter;

	/**
	 * Default Page. Has no logic for now
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String indexHandler(Map<String, Object> map)
			throws NoSuchMessageException {

		/*
		 * Read settings from messages.properties page How many characters we
		 * want in each column
		 */

		int indexDescriptionSize = Integer.parseInt(messageSource.getMessage(
				"indexDescriptionSize", null, Locale.getDefault()).trim());

		int indexTitleSize = Integer.parseInt(messageSource.getMessage(
				"indexTitleSize", null, Locale.getDefault()).trim());

		int indexLimit = Integer.parseInt(messageSource.getMessage(
				"indexLimit", null, Locale.getDefault()).trim());

		/*
		 * Since our list is already sorted in descending order. We want to
		 * display starting from 0 index (which is in fact from latest to oldest
		 * entry.
		 */

		map.put("messageList", formatter.formattedMessageList(indexTitleSize,
				indexDescriptionSize, 0, indexLimit, (Date) null));

		return "index";
	}

	/**
	 * Setup form
	 */
	@RequestMapping("/entry")
	public String entryForm(Model model, @RequestParam("date") String dateString)
			throws ParseException {

		Message message = new Message();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;

		if (!dateString.equals("")) {
			date = dateFormat.parse(dateString);
		} else {
			date = new Date();
		}

		// Required to preload date on the entry form
		message.setDateCreated(date);

		model.addAttribute("message", message);
		return "entry";
	}

	@RequestMapping("/edit_entry")
	public String editForm(Model model, @RequestParam("id") String idString)
			throws ParseException {

		Message message = messageService.getMessage(Integer.parseInt(idString));

		if (message == null)
			return "redirect: index.htm";

		model.addAttribute("message", message);
		return "edit_entry";

	}

	/**
	 * InitBinder to bind non-primitive types. Here it binds date with string on
	 * the web page
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/saveEntry")
	public String saveEntry(@ModelAttribute("message") Message message,
			BindingResult result, HttpServletRequest request) {
		
		// Format html < and > character before saving.		
		// message.setDescription(formatter.formatHTML(message.getDescription()));
		// message.setTitle(formatter.formatHTML(message.getTitle()));

		if (result.hasErrors()) {
			return "entry";
		}

		messageService.addMessage(message);

		/*
		 * Since dateCreated string has date in yyyy-MM-dd format. We have no
		 * problem forwarding it.
		 */

		return "redirect:/entry_list.htm?date="
				+ request.getParameter("dateCreated") + "&page";
	}

	// Do delete
	@RequestMapping(value = "/deleteEntry")
	public String deleteEntry(@RequestParam("id") String idString) {

		messageService.removeMessage(Integer.parseInt(idString));

		/*
		 * Since dateCreated string has date in yyyy-MM-dd format. We have no
		 * problem forwarding it.
		 */

		return "redirect:/entry_list.htm?date&page";
	}

	// Todo: There are still some inconsistencies that consisting a date
	// I have to retroinspect it, all
	@RequestMapping("/entry_list")
	public String entryList(Map<String, Object> map,
			@RequestParam("date") String dateString,
			@RequestParam("page") String pageNo) throws ParseException {

		int listDescriptionSize = Integer.parseInt(messageSource.getMessage(
				"listDescriptionSize", null, Locale.getDefault()).trim());

		int page = 1;
		if (!pageNo.equals(""))
			page = Integer.parseInt(pageNo);

		int listTitleSize = Integer.parseInt(messageSource.getMessage(
				"listTitleSize", null, Locale.getDefault()).trim());

		int pageLimit = Integer.parseInt(messageSource.getMessage("pageLimit",
				null, Locale.getDefault()).trim());

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		/*
		 * Let's create pagination
		 */
		int messageSize;

		int pages;

		/*
		 * If date was blank display today's date else find by date We have to
		 * take care of off by one error
		 */
		if (!dateString.equals("")) {
			Date date = dateFormat.parse(dateString);

			List<Message> tempMessageList = formatter.formattedMessageList(
					listTitleSize, listDescriptionSize, (page - 1) * pageLimit,
					(page - 1) * pageLimit + pageLimit, date);

			map.put("messageList", tempMessageList);
			messageSize = messageService.listMessage(date).size();

			pages = (messageSize - 1) / pageLimit;
		} else {

			// Sorry folks, we don't want to pass ambigious null value (May be
			// string or date)
			map.put("messageList", formatter.formattedMessageList(
					listTitleSize, listDescriptionSize, (page - 1) * pageLimit,
					(page - 1) * pageLimit + pageLimit, (Date) null));

			messageSize = messageService.listMessage().size();
			pages = (messageSize - 1) / pageLimit;
		}

		map.put("pages", pages);
		map.put("dateString", dateString);
		map.put("start", ((page - 1) * pageLimit));

		return "entry_list";
	}

	@RequestMapping("/search")
	public String searchList(Map<String, Object> map,
			@RequestParam("query") String query,
			@RequestParam("page") String pageNo) {

		int listDescriptionSize = Integer.parseInt(messageSource.getMessage(
				"listDescriptionSize", null, Locale.getDefault()).trim());

		int page = 1;
		if (!pageNo.equals(""))
			page = Integer.parseInt(pageNo);

		int listTitleSize = Integer.parseInt(messageSource.getMessage(
				"listTitleSize", null, Locale.getDefault()).trim());

		int pageLimit = Integer.parseInt(messageSource.getMessage("pageLimit",
				null, Locale.getDefault()).trim());

		/*
		 * Let's create pagination
		 */
		int messageSize;

		int pages;

		if (!query.equals("")) {

			List<Message> tempMessageList = formatter.formattedMessageList(
					listTitleSize, listDescriptionSize, (page - 1) * pageLimit,
					(page - 1) * pageLimit + pageLimit, query);

			map.put("messageList", tempMessageList);
			messageSize = messageService.listMessageByTitle(query).size();

			pages = (messageSize - 1) / pageLimit;

		} else {
			/*
			 * My fault. It should be "" not null, because we have treated this
			 * as an empty String on formattedMessageList
			 */
			map.put("messageList", formatter.formattedMessageList(
					listTitleSize, listDescriptionSize, (page - 1) * pageLimit,
					(page - 1) * pageLimit + pageLimit, ""));

			messageSize = messageService.listMessage().size();
			pages = (messageSize - 1) / pageLimit;
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(new Date());

		map.put("pages", pages);
		map.put("dateString", dateString);
		map.put("start", ((page - 1) * pageLimit));
		map.put("queryString", query);

		return "search";
	}

}
