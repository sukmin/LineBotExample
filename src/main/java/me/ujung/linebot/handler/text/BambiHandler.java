package me.ujung.linebot.handler.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;

import me.ujung.linebot.model.TextInstruction;

@Component
public class BambiHandler implements TextHandleable {

	@Autowired
	private LineBotClient lineBotClient;

	@Override
	public void handle(TextInstruction instruction) throws LineBotAPIException {
		lineBotClient.sendImage(instruction.getFrom(), "http://static.dog.ujung.me/20160530_184253.jpg", "http://static.dog.ujung.me/20160530_184253.jpg");
	}

	@Override
	public String[] getOperation() {
		return new String[] { "밤비" };
	}

}
