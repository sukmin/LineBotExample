package me.ujung.linebot.controller;

import java.util.List;

import me.ujung.linebot.service.LineBotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.callback.Message;
import com.linecorp.bot.model.content.AbstractContent;
import com.linecorp.bot.spring.boot.annotation.LineBotMessages;

@RestController
public class LineController {

	@Autowired
	private LineBotService defaultLineBotService;

	@RequestMapping("/callback")
	public void callback(@LineBotMessages List<Message> messages) throws LineBotAPIException {
		for (Message aMessage : messages) {
			defaultLineBotService.listen((AbstractContent) aMessage.getContent());
		}
	}

}
