package com.springlec.base0601.command;

import org.springframework.ui.Model;
//request다 들어있는게 모델 

public interface BCommand {

	void execute(Model model);
	
	
}//end
