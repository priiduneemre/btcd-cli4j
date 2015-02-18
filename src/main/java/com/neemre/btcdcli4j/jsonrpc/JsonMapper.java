package com.neemre.btcdcli4j.jsonrpc;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.neemre.btcdcli4j.common.Defaults;

public class JsonMapper {
	
	private ObjectMapper rawMapper;
	private ObjectWriter rawWriter;
	
	
	public JsonMapper() {
		rawMapper = new ObjectMapper();
		rawWriter = rawMapper.writer().withDefaultPrettyPrinter();
	}
	
	public <T> String mapToJson(T entity) {
		try {
			String entityJson = rawWriter.writeValueAsString(entity);
			return entityJson;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public <T> T mapToEntity(String entityJson, Class<T> clazz) {
		try {
			T entity = rawMapper.readValue(entityJson, clazz);
			return entity;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String decode(String stringJson) {
		String string = unescapeJson(stringJson.substring(1, stringJson.length() - 1));
		return string;
	}
	
	public BigDecimal decodeAsBigDecimal(String bigDecimalJson) {
		BigDecimal bigDecimal = new BigDecimal(bigDecimalJson).setScale(Defaults.DECIMAL_SCALE,
				Defaults.DECIMAL_ROUNDING_MODE);
		return bigDecimal;
	}
	
	public String unescapeJson(String json) {
		return StringEscapeUtils.unescapeJson(json);
	}
}