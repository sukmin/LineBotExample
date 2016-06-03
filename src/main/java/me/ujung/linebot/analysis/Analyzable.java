package me.ujung.linebot.analysis;

import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.content.AbstractContent;
import com.linecorp.bot.model.content.ContentType;

public interface Analyzable<T extends AbstractContent> {

	public ContentType getContentType();

	public void analyze(T content) throws LineBotAPIException;

}
