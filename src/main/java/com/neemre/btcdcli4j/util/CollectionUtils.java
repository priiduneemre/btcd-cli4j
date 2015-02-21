package com.neemre.btcdcli4j.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class CollectionUtils {

	private CollectionUtils() {}

	@SafeVarargs
	public static <T> List<T> asList(T... items) {
		List<T> itemsList = new ArrayList<T>(items.length);
		for(T item : items) {
			itemsList.add(item);
		}
		return itemsList;
	}

	@SafeVarargs
	public static <T> List<T> merge(List<T>... lists) {
		List<T> mergedList = new ArrayList<T>();
		for(List<T> list : lists) {
			mergedList.addAll(list);
		}
		return mergedList;
	}

	@SafeVarargs
	public static <T> List<T> mergeInterlaced(List<T>... lists) {
		if(!equalsSize(lists)) {
			throw new IllegalArgumentException("I am broken.");	//TODO
		}
		List<T> mergedList = new ArrayList<T>();
		if(lists.length > 0) {
			for(int i = 0; i < lists[0].size(); i++) {
				for(List<T> list : lists) {
					mergedList.add(list.get(i));
				}
			}
		}
		return mergedList;	
	}

	@SafeVarargs
	public static <T> boolean equalsSize(List<T>... lists) {
		Set<Integer> sizes = new HashSet<Integer>(lists.length);
		for(List<T> list : lists) {
			if(list == null) {
				return false;
			}
			if(sizes.isEmpty()) {
				sizes.add(list.size());
			} else {
				if(sizes.add(list.size())) {
					return false;
				}
			}
		}
		return true;
	}
}