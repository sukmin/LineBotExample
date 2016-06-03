package me.ujung.linebot.handler.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;

import me.ujung.linebot.model.TextInstruction;

@Component
public class AoHandler implements TextHandleable {

	private static final Logger logger = LoggerFactory.getLogger(AoHandler.class);

	@Autowired
	private LineBotClient lineBotClient;

	@Override
	public void handle(TextInstruction instruction) throws LineBotAPIException {
		int count = 1;
		try {
			count = Integer.parseInt(instruction.getArgs(0));
		} catch (NumberFormatException e) {
			logger.warn("잘못된 인자 사용");
		}

		count = Math.max(count, 1);
		count = Math.min(count, 100);

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < count; i++) {
			builder.append("아오");
		}

		lineBotClient.sendText(instruction.getFrom(), builder.toString());
	}

	@Override
	public String[] getOperation() {
		return new String[] { "아오", "아오빡" };
	}

}
