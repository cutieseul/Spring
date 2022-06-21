package com.springlec.base0601.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.base0601.command.BCommand;
import com.springlec.base0601.command.BContentCommand;
import com.springlec.base0601.command.BDelete;
import com.springlec.base0601.command.BListCommand;
import com.springlec.base0601.command.BModifyCommand;
import com.springlec.base0601.command.BWriteCommand;

@Controller
public class BController {
		BCommand command = null;
		//전체 보여주기
		@RequestMapping("/list")
		public String list(Model model) {
			
			System.out.println("list()");
			command = new BListCommand();
			command.execute(model);
			
			return "list";
		}
		//작성하는 페이지 가기
		@RequestMapping("/write")
		public String write() {
			return "write_view";
		}
		
		//새로작성하기
		@RequestMapping("/insert")
		public String insert(HttpServletRequest request, Model model) {
			System.out.println("insert()");
			model.addAttribute("request", request);
			command = new BWriteCommand();
			command.execute(model);
			
			return "redirect:list";
		}
		//하나씩 내용 보여주기
		@RequestMapping("/content")
		public String content(HttpServletRequest request, Model model) {
			
			System.out.println("content()");
			model.addAttribute("request", request);
			command = new BContentCommand();
			command.execute(model);
			
			return "content_view";
		}
		//수정하기
		@RequestMapping("/modify")
		public String modify(HttpServletRequest request, Model model) {
			System.out.println("modify()");
			model.addAttribute("request", request);
			command = new BModifyCommand();
			command.execute(model);
			
			return "redirect:list";
		}
		//삭제하기
		@RequestMapping("/delete")
		public String delete(HttpServletRequest request, Model model) {
			System.out.println("delete()");
			model.addAttribute("request", request);
			command = new BDelete();
			command.execute(model);
			
			return "redirect:list";
		}
}//end
