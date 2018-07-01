package helpers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

	public static String prettyJSON(String jsonString) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse
		Object jsonObject = mapper.readValue(jsonString, Object.class);
		String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
		prettyJson = UtilityHelper.replaceString(prettyJson);
		prettyJson = UtilityHelper.replaceDoubleQuotes(prettyJson);

		return prettyJson;
	}

	public static String prettyArray(String stringArray) {
		StringBuilder prettyString = new StringBuilder();
		stringArray = UtilityHelper.replaceString(stringArray);
		stringArray = UtilityHelper.replaceDoubleQuotes(stringArray);

		String[] stringArrayNew = stringArray.split(",");

		for (String value : stringArrayNew) {
			prettyString.append(value+"\n");
		}

		return prettyString.toString();
	}

	public static String prettyRedisJson(String redisKey, String redisValue) throws JsonParseException, JsonMappingException, IOException {
		if(redisKey.equals("email_address")) {
			return prettyArray(redisValue);
		} else {
			return prettyJSON(redisValue);
		}
	}
}
