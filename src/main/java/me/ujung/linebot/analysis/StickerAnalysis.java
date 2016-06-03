package me.ujung.linebot.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.content.ContentType;
import com.linecorp.bot.model.content.StickerContent;

@Component
public class StickerAnalysis implements Analyzable<StickerContent> {

	@Autowired
	private LineBotClient lineBotClient;

	@Override
	public ContentType getContentType() {
		return ContentType.STICKER;
	}

	@Override
	public void analyze(StickerContent content) throws LineBotAPIException {
		lineBotClient.sendText(content.getFrom(), "스티커는 취급하지 않습니다(--)(__)");
	}

}
