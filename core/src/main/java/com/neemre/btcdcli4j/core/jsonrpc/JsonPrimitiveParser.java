package com.neemre.btcdcli4j.core.jsonrpc;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringEscapeUtils;

import com.neemre.btcdcli4j.core.common.Constants;
import com.neemre.btcdcli4j.core.common.Defaults;

public class JsonPrimitiveParser {

	public Integer parseInteger(String integerJson) {
		try {
			return Integer.valueOf(integerJson);		
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public Long parseLong(String longJson) {
		try {
			return Long.valueOf(longJson);		
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public BigDecimal parseBigDecimal(String bigDecimalJson) {
		try {
			return new BigDecimal(bigDecimalJson).setScale(Defaults.DECIMAL_SCALE, 
					Defaults.ROUNDING_MODE);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public Boolean parseBoolean(String booleanJson) {
		if(!booleanJson.equals(Constants.STRING_NULL)) {
			return Boolean.valueOf(booleanJson);
		} else {
			return null;
		}
	}

	public String parseString(String stringJson) {
		if(!stringJson.equals(Constants.STRING_NULL)) {
			return unescapeJson(stringJson.replaceAll("^\"|\"$", ""));
		} else {
			return null;
		}
	}

	public String unescapeJson(String json) {
		return StringEscapeUtils.unescapeJson(json);
	}
}