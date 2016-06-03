package me.ujung.linebot.handler.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;

import me.ujung.linebot.model.TextInstruction;

@Component
public class HelloHandler implements TextHandleable {
	
	@Autowired
	private LineBotClient lineBotClient;

	@Override
	public void handle(TextInstruction instruction) throws LineBotAPIException {
		lineBotClient.sendText(instruction.getFrom(), "안녕하세요!");
	}

	@Override
	public String[] getOperation() {
		return new String[] { "안녕", "hello" };
	}

}
