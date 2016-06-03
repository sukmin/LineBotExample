package me.ujung.linebot.service;

import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.content.AbstractContent;

public interface LineBotService {

	public void listen(AbstractContent content) throws LineBotAPIException;

}
