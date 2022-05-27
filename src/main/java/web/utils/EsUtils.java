package web.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * * @author zhao_yifan * @date 2022/3/30 * @describe es解析结果工具类
 **/
public class EsUtils {

  private final static String TIME = "TIME";

  public static JsonArray JsonObjectAnalysis(SearchResponse response) {
    return JsonObjectAnalysis(JsonParser.parseString(response.toString()).getAsJsonObject());
  }

  public static JsonArray JsonObjectAnalysis(JsonObject jsonObject) {
    return JsonParser.parseString(jsonObject.toString()).getAsJsonObject().getAsJsonObject("hits")
        .getAsJsonArray("hits");
  }

  public static JsonArray JsonArrayAggregation(SearchResponse response, String name) {
    if (response.getAggregations() == null) {
      return null;
    }

    return JsonParser.parseString(response.toString()).getAsJsonObject()
        .getAsJsonObject("aggregations")
        .getAsJsonObject(name).getAsJsonArray("buckets");
  }

  public static <T> void setResultField(JsonObject jsonObject, T esEntity) {
    Field[] fields = esEntity.getClass().getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      try {
        field.set(esEntity, jsonObject.get(field.getName()) == null ? ""
            : jsonObject.get(field.getName()).toString());
      } catch (IllegalAccessException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    }
  }

  public static <T> void setResultField(JsonArray jsonArray, List<T> esEntity) {
    Field[] fields = esEntity.getClass().getGenericInterfaces().getClass().getDeclaredFields();
    for (JsonElement jsonElement : jsonArray) {
      JsonObject jsonObject = jsonElement.getAsJsonObject().getAsJsonObject("_source");
      for (Field field : fields) {
        field.setAccessible(true);
        try {
          field.set(esEntity, jsonArray.get(0).getAsJsonObject().get(field.getName()) == null ? ""
              : jsonArray.get(0).getAsJsonObject().get(field.getName()).getAsString());
        } catch (IllegalAccessException e) {
          e.printStackTrace();
          throw new RuntimeException(e);
        }
      }
    }
  }

  public static RangeQueryBuilder TimeSet(String fromTime, String toTime) {
    if (fromTime == null || toTime == null) {
      Date date = new Date();
      fromTime = DateUtils.DateToString(DateUtils.addDay(date, DateUtils.DEFAULT));
      toTime = DateUtils.DateToString(date);
    }
    return QueryBuilders.rangeQuery(TIME).gte(fromTime).lte(toTime).format("yyyy-MM-dd HH:mm:ss");
  }

  public static RangeQueryBuilder TimeSet(LocalTime fromTime, LocalTime toTime) {
    return TimeSet(fromTime, toTime, "yyyy-MM-dd HH:mm:ss");
  }

  public static RangeQueryBuilder TimeSet(LocalTime fromTime, LocalTime toTime, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    if (fromTime == null || toTime == null) {
      LocalTime localTime = LocalTime.now();
      fromTime = localTime.plusHours(DateUtils.DEFAULT / 24);
      toTime = localTime;
    }
    return QueryBuilders.rangeQuery(TIME).gte(formatter.format(fromTime)).lte(formatter.format(toTime)).format("yyyy-MM-dd HH:mm:ss");
  }

  public static RangeQueryBuilder TimeSet(Date date) {
    return QueryBuilders.rangeQuery(TIME)
        .gte(DateUtils.DateToString(DateUtils.addDay(date, DateUtils.DEFAULT)))
        .lte(DateUtils.DateToString(date)).format("yyyy-MM-dd HH:mm:ss");
  }

}
