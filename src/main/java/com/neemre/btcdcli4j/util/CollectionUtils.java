package com.neemre.btcdcli4j.util;

import java.util.ArrayList;
import java.util.List;

public final class CollectionUtils {

	private CollectionUtils() {}
	
	public static List<Object> asList(Object... items) {
		List<Object> itemsList = new ArrayList<Object>(items.length);
		for(Object item : items) {
			itemsList.add(item);
		}
		return itemsList;
	}
}