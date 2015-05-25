package com.neemre.btcdcli4j.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.neemre.btcdcli4j.core.common.Constants;
import com.neemre.btcdcli4j.core.common.Errors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {
	
	public static String capitalize(String text) {
		if (text == null) {
			throw new IllegalArgumentException(Errors.ARGS_NULL.getDescription());
		}
		if (text.length() == 0) {
			return text;
		}
		StringBuilder result = new StringBuilder();
		result.append(Character.toTitleCase(text.charAt(0))).append(text.substring(1));
		return result.toString();
	}
	
	public static String pad(String text, int newLength, char padWith, boolean isLeading) {
		if (text == null) {
			throw new IllegalArgumentException(Errors.ARGS_NULL.getDescription());
		}
		StringBuilder result = new StringBuilder();
		while (result.length() < newLength - text.length()) {
			result.append(padWith);
		}
		if (isLeading) {
			result.append(text);
		} else {
			result.insert(0, text);
		}
		return result.toString();
	}

	public static String random(int length, String alphabet) {
		if (alphabet == null) {
			throw new IllegalArgumentException(Errors.ARGS_NULL.getDescription());
		}
		if (length < 0) {
			throw new IllegalArgumentException(Errors.ARGS_VALUE_NEGATIVE.getDescription());
		} else if (length == 0) {
			return "";
		} else {
			Random random = new Random();
			char[] text = new char[length];
			for (int i = 0; i < length; i++) {
				text[i] = alphabet.charAt(random.nextInt(alphabet.length()));
			}
			return new String(text);
		}
	}
	
	public static String repeat(char character, int count) {
		return new String(new char[count]).replaceAll(Character.toString(Constants.CHAR_NULL), 
				Character.toString(character));
	}
	
	public static List<String> split(String text, int fragmentLength) {
		if (text == null) {
			throw new IllegalArgumentException(Errors.ARGS_NULL.getDescription());
		}
		List<String> fragments = new ArrayList<String>();
		for (int i = 0; i < text.length(); i += fragmentLength) {
			fragments.add(text.substring(i, Math.min(text.length(), i + fragmentLength)));
		}
		return fragments;
	}
	
	public static <T> String join(List<T> items) {
		if (items == null) {
			throw new IllegalArgumentException(Errors.ARGS_NULL.getDescription());
		}
		StringBuilder result = new StringBuilder();
		Iterator<T> i = items.iterator();
		while (i.hasNext()) {
			T item = i.next();
			if (item == null) {
				result.append(Constants.STRING_EMPTY);
			} else {
				result.append(item.toString());
			}
		}
		return result.toString();
	}
	
	public static List<String> replaceAll(List<String> texts, String regex, String replacement) {
		if (texts == null) {
			throw new IllegalArgumentException(Errors.ARGS_NULL.getDescription());
		}
		for (int i = 0; i < texts.size(); i++) {
			texts.set(i, texts.get(i).replaceAll(regex, replacement));
		}
		return texts;
	}
}