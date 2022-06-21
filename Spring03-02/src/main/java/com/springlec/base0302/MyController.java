package com.springlec.base0302;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller		//컨트롤러로 사용한다고 선언 해주기
public class MyController {
	@RequestMapping("/content")
	public String content(Model model) {
		model.addAttribute("id", "tester");
		return "content";
	}


}
