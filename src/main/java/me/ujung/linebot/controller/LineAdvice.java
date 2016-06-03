package me.ujung.linebot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.linecorp.bot.client.exception.LineBotAPIException;

@ControllerAdvice
public class LineAdvice {

	private static final Logger logger = LoggerFactory.getLogger(LineAdvice.class);

	@ExceptionHandler(LineBotAPIException.class)
	public void lineBotApiException(LineBotAPIException e) {
		logger.error("라인봇 API 오류", e);
	}

}
