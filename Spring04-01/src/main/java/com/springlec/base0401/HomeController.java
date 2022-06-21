package com.springlec.base0401;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	
	
	
	//1번방식
	@RequestMapping("board/confirmid")
	public String confirmid(HttpServletRequest request, Model model) {
		
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "board/confirmid";
	}
	
	
	//2번방식
	@RequestMapping("board/checkId")
	public String checkId(@RequestParam("id") String id, @RequestParam("pw") int pw, Model model ) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "board/confirmid";	//jsp 하나더 안만들려고 일로 보냄
		
	}
	
	
	
	
	@RequestMapping("member/join")
	public String joinData(@RequestParam("name") String name,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("email") String email,
			Model model) {
		
		Member member = new Member();
		member.setName(name);
		member.setId(id);
		member.setPw(pw);
		member.setEmail(email);
		
		model.addAttribute("member",member);
		
		
	
	
		return "member/join";
	}
	
	
	
	@RequestMapping("member/join")
	public String joinData(Member member) {
		
		return "member/join";		//.jsp 안써줘도 됨
	}
	
	
	
	
	
	//studentView.jsp 
	@RequestMapping("student/{studentId}")
	public String getStudent(@PathVariable String studentId, Model model) {
		model.addAttribute("studentId", studentId);
		
		return "student/studentView";
	}
	
	
	
	
	@RequestMapping("user/logIn")
	public String logIn() {
		
		return "user/logIn";
	}
	
	
	
	@RequestMapping("user/result")
	public String logInData(User user) {
		
		return "user/result";		//.jsp 안써줘도 됨
	}
	
	
	
	
	
}
