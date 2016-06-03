package me.ujung.linebot.model;

import java.util.Arrays;

import com.linecorp.bot.model.content.TextContent;

public class TextInstruction {

	private static final int OPERATION = 0;

	private String operation;
	private String[] args;
	private TextContent original;

	public TextInstruction(TextContent original) {
		this.original = original;
		String sentence = original.getText().trim();
		String[] wordes = sentence.split("\\s+"); // 모든 공백 스플릿

		this.operation = wordes[OPERATION];
		if (wordes.length >= 2) {
			this.args = Arrays.copyOfRange(wordes, OPERATION + 1, wordes.length);
		} else {
			this.args = new String[0];
		}
	}

	public String getId() {
		return this.original.getId();
	}

	public String getFrom() {
		return this.original.getFrom();
	}

	public String getOperation() {
		return this.operation;
	}

	public String[] getArgs() {
		return this.args;
	}

	public String getArgString() {
		return String.join(" ", Arrays.asList(this.args));
	}

	public boolean hasArgs() {
		return this.args.length > 0;
	}

	public boolean hasArgs(int length) {
		if (length <= 0) {
			return false;
		}
		return this.args.length >= length;
	}

	public String getArgs(int index) {
		if (this.args.length <= index) {
			return "";
		}
		return this.args[index];
	}

}
