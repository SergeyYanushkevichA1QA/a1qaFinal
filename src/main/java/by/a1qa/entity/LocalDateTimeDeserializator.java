package by.a1qa.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializator extends StdDeserializer<LocalDateTime> {

    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss.S";

    public LocalDateTimeDeserializator() {
        super(LocalDateTime.class);
    }

    public LocalDateTimeDeserializator(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        LocalDateTime dateTime = null;
        String time = jsonParser.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
        dateTime = LocalDateTime.parse(time, formatter);
        return dateTime;
    }
}
