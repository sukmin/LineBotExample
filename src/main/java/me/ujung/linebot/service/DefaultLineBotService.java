package me.ujung.linebot.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import me.ujung.linebot.analysis.Analyzable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.content.AbstractContent;
import com.linecorp.bot.model.content.ContentType;

@SuppressWarnings("rawtypes")
@Service
public class DefaultLineBotService implements LineBotService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultLineBotService.class);

	@Autowired
	private LineBotClient lineBotClient;

	@Autowired
	private List<Analyzable> analyzables;

	private Map<ContentType, Analyzable> analysises;

	@PostConstruct
	private void init() {
		logger.info("초기화 시작");
		Map<ContentType, Analyzable> analysisMap = new HashMap<>();
		for (Analyzable aAnalyzable : analyzables) {
			ContentType lineContentType = aAnalyzable.getContentType();
			if (analysisMap.containsKey(lineContentType)) {
				throw new RuntimeException(DefaultLineBotService.class.getSimpleName() + " 중복된 컨텐트 타입이 존재합니다. ContentType : " + lineContentType.name());
			}
			analysisMap.put(lineContentType, aAnalyzable);
			logger.info("{} 타입 초기화", lineContentType.name());
		}
		analysises = Collections.unmodifiableMap(analysisMap);
		logger.info("초기화 완료 총 {}개의 타입 사용", analysises.size());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void listen(AbstractContent content) throws LineBotAPIException {
		logger.info("봇수신 id :{}, from :{}, ContentType :{} ", content.getId(), content.getFrom(), content.getContentType().name());

		ContentType lineContentType = content.getContentType();
		if (analysises.containsKey(lineContentType)) {
			Analyzable aAnalyzable = analysises.get(lineContentType);
			aAnalyzable.analyze(content);
		} else {
			lineBotClient.sendText(content.getFrom(), "아직 구현되지 않은 기능입니다. ㅜㅠ");
			logger.warn("미구현 타입 실행 ContentType : {}", lineContentType);
		}
	}

}
