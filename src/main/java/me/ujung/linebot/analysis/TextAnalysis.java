package me.ujung.linebot.analysis;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import me.ujung.linebot.handler.text.TextHandleable;
import me.ujung.linebot.model.TextInstruction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.content.ContentType;
import com.linecorp.bot.model.content.TextContent;

@Component
public class TextAnalysis implements Analyzable<TextContent> {

	private static final Logger logger = LoggerFactory.getLogger(TextAnalysis.class);

	@Autowired
	private LineBotClient lineBotClient;

	@Autowired
	private List<TextHandleable> textHandleables;

	private Map<String, TextHandleable> textHandlers;

	@PostConstruct
	private void init() {
		logger.info("초기화 시작");
		Map<String, TextHandleable> textHandlerMap = new HashMap<>();
		for (TextHandleable aTextHandleable : textHandleables) {
			String[] operations = aTextHandleable.getOperation();
			for (String aOperation : operations) {
				if (textHandlerMap.containsKey(aOperation)) {
					throw new RuntimeException("중복된 명령어가 존재합니다. operation : " + aOperation);
				}
				textHandlerMap.put(aOperation, aTextHandleable);
				logger.info("{} 명령어 초기화", aOperation);
			}
		}
		textHandlers = Collections.unmodifiableMap(textHandlerMap);
		logger.info("초기화 완료 총 {}개의 명령어", textHandlers.size());
	}

	@Override
	public ContentType getContentType() {
		return ContentType.TEXT;
	}

	@Override
	public void analyze(TextContent content) throws LineBotAPIException {

		TextInstruction aInstruction = new TextInstruction(content);
		if (textHandlers.containsKey(aInstruction.getOperation())) {
			TextHandleable aTextHandleable = textHandlers.get(aInstruction.getOperation());
			aTextHandleable.handle(aInstruction);
		} else {
			lineBotClient.sendText(aInstruction.getFrom(), "해당 명령은 유효하지 않습니다.");
		}

	}

}
