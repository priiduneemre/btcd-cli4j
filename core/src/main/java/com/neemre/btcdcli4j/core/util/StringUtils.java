package com.neemre.btcdcli4j.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.neemre.btcdcli4j.core.common.Constants;
import com.neemre.btcdcli4j.core.common.Errors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {
	
	public static String pad(String text, int newLength, char padWith, boolean isLeading) {
		if (text == null) {
			throw new IllegalArgumentException(Errors.ARGS_NULL.getDescription());
		}
		StringBuilder result = new StringBuilder();
		while (result.length() < newLength - text.length()) {
			result.append(padWith);
		}
		if(isLeading) {
			result.append(text);
		} else {
			result.insert(0, text);
		}
		return result.toString();
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
		if(texts == null) {
			throw new IllegalArgumentException(Errors.ARGS_NULL.getDescription());
		}
		for(int i = 0; i < texts.size(); i++) {
			texts.set(i, texts.get(i).replaceAll(regex, replacement));
		}
		return texts;
	}
}