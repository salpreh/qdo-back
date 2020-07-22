package com.salpreh.qdo.configurations.jackson.serializers;

import java.io.IOException;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("rawtypes")
public class PageSerializer extends StdSerializer<Page> {
	
	private static final long serialVersionUID = 1L;

	public PageSerializer() {
		this(null);
	}

	public PageSerializer(Class<Page> c) {
		super(c);
	}

	@Override
	public void serialize(Page value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeArrayFieldStart("data");
		for (Object item : value.getContent()) {
			gen.writeObject(item);
		}
		gen.writeEndArray();

		gen.writeNumberField("pages", value.getTotalPages());
		gen.writeNumberField("elements", value.getTotalElements());
		gen.writeEndObject();
	}

}
