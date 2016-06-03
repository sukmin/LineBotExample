package me.ujung.linebot.handler.text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;

import me.ujung.linebot.model.TextInstruction;

@Component
public class NamuWikiHandler implements TextHandleable {

	private static final String NAMU_WIKI_SEARCH_URL = "https://namu.wiki/search/";

	private static final Logger logger = LoggerFactory.getLogger(NamuWikiHandler.class);

	@Autowired
	private LineBotClient lineBotClient;

	@Override
	public void handle(TextInstruction instruction) throws LineBotAPIException {
		String result = null;
		if (instruction.hasArgs()) {
			String searchWord = instruction.getArgString();
			try {
				result = NAMU_WIKI_SEARCH_URL + URLEncoder.encode(searchWord, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("주소생성중 오류가 발생하였습니다. 검색단어 : {}", searchWord);
				result = "주소생성중 오류가 발생하였습니다.";
			}
		} else {
			result = "검색할 단어를 입력해주어야 합니다.";
		}
		lineBotClient.sendText(instruction.getFrom(), result);
	}

	@Override
	public String[] getOperation() {
		return new String[] { "나무", "나무위키", "ㄴㅁ" };
	}

}
