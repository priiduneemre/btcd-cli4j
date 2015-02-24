package com.neemre.btcdcli4j.jsonrpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;

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

	public <T> T mapToEntity(String entityJson, Class<T> entityClass) {
		try {
			T entity = rawMapper.readValue(entityJson, entityClass);
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

	public <T, S> Map<T, S> mapToMap(String entitiesJson, Class<T> keyClass, Class<S> valueClass) {
		try {
			MapType mapType = rawMapper.getTypeFactory().constructMapType(HashMap.class, keyClass,
					valueClass);
			Map<T, S> entities = rawMapper.readValue(entitiesJson, mapType);
			return entities;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public <T> List<T> mapToList(String entitiesJson, Class<T> entityClass) {
		try {
			CollectionType collectionType = rawMapper.getTypeFactory().constructCollectionType(
					ArrayList.class, entityClass);
			List<T> entities = rawMapper.readValue(entitiesJson, collectionType);
			return entities;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}