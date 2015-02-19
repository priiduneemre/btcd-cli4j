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
	
	public Boolean parseBoolean(String booleanJson) {
		return Boolean.valueOf(booleanJson);
	}
	
	public BigDecimal parseBigDecimal(String bigDecimalJson) {
		return new BigDecimal(bigDecimalJson).setScale(Defaults.DECIMAL_SCALE,
				Defaults.DECIMAL_ROUNDING_MODE);
	}
	
	public Integer parseInteger(String integerJson) {
		return Integer.valueOf(integerJson);
	}

	public String parseString(String stringJson) {
		return unescapeJson(stringJson.substring(1, stringJson.length() - 1));
	}
	
	public String unescapeJson(String json) {
		return StringEscapeUtils.unescapeJson(json);
	}
}