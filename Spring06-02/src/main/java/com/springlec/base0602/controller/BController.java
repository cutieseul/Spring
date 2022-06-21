package com.springlec.base0602.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.base0602.command.BCommand;
import com.springlec.base0602.command.BContentCommand;
import com.springlec.base0602.command.BDelete;
import com.springlec.base0602.command.BListCommand;
import com.springlec.base0602.command.BModifyCommand;
import com.springlec.base0602.command.BWriteCommand;

@Controller
public class BController {
		BCommand command = null;
		private BCommand listCommand = null;
		private BCommand writeCommand = null;
		private BCommand contentCommand = null;
		private BCommand modifyCommand = null;
		private BCommand deleteCommand = null;
		
		
		@Autowired
		public void auto(BCommand list, BCommand write, BCommand content, BCommand modify, BCommand delete) {
			this.listCommand = list;
			this.writeCommand = write;
			this.contentCommand = content;
			this.modifyCommand = modify;
			this.deleteCommand = delete;
		}
		
	
		
		
		
		
		
		//전체 보여주기
		@RequestMapping("/list")
		public String list(Model model) {
			
			System.out.println("list()");
//			command = new BListCommand();
//			command.execute(model);
			listCommand.execute(model);
			
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
//			command = new BWriteCommand();
//			command.execute(model);
			writeCommand.execute(model);

			return "redirect:list";
		}
		
		
		
		//하나씩 내용 보여주기
		@RequestMapping("/content")
		public String content(HttpServletRequest request, Model model) {
			
			System.out.println("content()");
			model.addAttribute("request", request);
//			command = new BContentCommand();
//			command.execute(model);
			contentCommand.execute(model);
			
			return "content_view";
		}
		
		
		
		
		
		//수정하기
		@RequestMapping("/modify")
		public String modify(HttpServletRequest request, Model model) {
			System.out.println("modify()");
			model.addAttribute("request", request);
//			command = new BModifyCommand();
//			command.execute(model);
			modifyCommand.execute(model);

			
			return "redirect:list";
		}
		//삭제하기
		@RequestMapping("/delete")
		public String delete(HttpServletRequest request, Model model) {
			System.out.println("delete()");
			model.addAttribute("request", request);
//			command = new BDelete();
//			command.execute(model);
			deleteCommand.execute(model);

			return "redirect:list";
		}
}//end
