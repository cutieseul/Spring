package com.springlec.base0501;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	
	
	
	@RequestMapping("studentOK")
	public String studentOK11()		{	//메소드 이름 아무 의미 없음
		return "student/studentOK";
		
	}
	@RequestMapping("studentNG")
	public String studentOK22()		{	
		return "student/studentNG";
		
	}
	
	@RequestMapping("studentConfirm")
	public String studentOK33(HttpServletRequest request) {
		
		String returnStatement = "";
		
		String id = request.getParameter("id");
		if(id.equals("abc")) {
			returnStatement = "studentOK";
		}else {
			returnStatement = "studentNG";
		}
		
		return "redirect:" + returnStatement;
	}
	
	
	
}
