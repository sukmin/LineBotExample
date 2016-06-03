package me.ujung.linebot.handler.text;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;

import me.ujung.linebot.model.TextInstruction;

@Component
public class MemoHandler implements TextHandleable {

	private ConcurrentHashMap<String, String> memory = new ConcurrentHashMap<>();

	@Autowired
	private LineBotClient lineBotClient;

	@Override
	public void handle(TextInstruction instruction) throws LineBotAPIException {
		String from = instruction.getFrom();

		// 메모를 저장할 때
		if (instruction.hasArgs()) {
			memory.put(instruction.getFrom(), instruction.getArgString());
			lineBotClient.sendText(from, "저장되었습니다.");
			return;
		}

		// 메모를 읽을 때
		if (memory.containsKey(from)) {
			lineBotClient.sendText(from, memory.get(from));
			return;
		}

		// 저장된 메모가 없는 경우
		lineBotClient.sendText(from, "현재 저장된 메모가 없습니다.");
		return;
	}

	@Override
	public String[] getOperation() {
		return new String[] { "메모", "ㅁㅁ" };
	}

}
