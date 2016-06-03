package me.ujung.linebot.handler.text;

import com.linecorp.bot.client.exception.LineBotAPIException;

import me.ujung.linebot.model.TextInstruction;

public interface TextHandleable {
	public void handle(TextInstruction instruction) throws LineBotAPIException;

	public String[] getOperation();
}
