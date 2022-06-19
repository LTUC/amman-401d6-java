package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Suggestion type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Suggestions")
public final class Suggestion implements Model {
  public static final QueryField ID = field("Suggestion", "id");
  public static final QueryField NAME = field("Suggestion", "name");
  public static final QueryField DESC = field("Suggestion", "desc");
  public static final QueryField TIMESTAMP = field("Suggestion", "timestamp");
  public static final QueryField USER_SUGGESTIONS_ID = field("Suggestion", "userSuggestionsId");
  public static final QueryField CATEGORY_SUGGESTIONS_ID = field("Suggestion", "categorySuggestionsId");
  public static final QueryField WEATHER_SUGGESTIONS_ID = field("Suggestion", "weatherSuggestionsId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String", isRequired = true) String desc;
  private final @ModelField(targetType="String", isRequired = true) String timestamp;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String userSuggestionsId;
  private final @ModelField(targetType="ID") String categorySuggestionsId;
  private final @ModelField(targetType="ID") String weatherSuggestionsId;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getDesc() {
      return desc;
  }
  
  public String getTimestamp() {
      return timestamp;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getUserSuggestionsId() {
      return userSuggestionsId;
  }
  
  public String getCategorySuggestionsId() {
      return categorySuggestionsId;
  }
  
  public String getWeatherSuggestionsId() {
      return weatherSuggestionsId;
  }
  
  private Suggestion(String id, String name, String desc, String timestamp, String userSuggestionsId, String categorySuggestionsId, String weatherSuggestionsId) {
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.timestamp = timestamp;
    this.userSuggestionsId = userSuggestionsId;
    this.categorySuggestionsId = categorySuggestionsId;
    this.weatherSuggestionsId = weatherSuggestionsId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Suggestion suggestion = (Suggestion) obj;
      return ObjectsCompat.equals(getId(), suggestion.getId()) &&
              ObjectsCompat.equals(getName(), suggestion.getName()) &&
              ObjectsCompat.equals(getDesc(), suggestion.getDesc()) &&
              ObjectsCompat.equals(getTimestamp(), suggestion.getTimestamp()) &&
              ObjectsCompat.equals(getCreatedAt(), suggestion.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), suggestion.getUpdatedAt()) &&
              ObjectsCompat.equals(getUserSuggestionsId(), suggestion.getUserSuggestionsId()) &&
              ObjectsCompat.equals(getCategorySuggestionsId(), suggestion.getCategorySuggestionsId()) &&
              ObjectsCompat.equals(getWeatherSuggestionsId(), suggestion.getWeatherSuggestionsId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getDesc())
      .append(getTimestamp())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getUserSuggestionsId())
      .append(getCategorySuggestionsId())
      .append(getWeatherSuggestionsId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Suggestion {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("desc=" + String.valueOf(getDesc()) + ", ")
      .append("timestamp=" + String.valueOf(getTimestamp()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("userSuggestionsId=" + String.valueOf(getUserSuggestionsId()) + ", ")
      .append("categorySuggestionsId=" + String.valueOf(getCategorySuggestionsId()) + ", ")
      .append("weatherSuggestionsId=" + String.valueOf(getWeatherSuggestionsId()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Suggestion justId(String id) {
    return new Suggestion(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      desc,
      timestamp,
      userSuggestionsId,
      categorySuggestionsId,
      weatherSuggestionsId);
  }
  public interface NameStep {
    DescStep name(String name);
  }
  

  public interface DescStep {
    TimestampStep desc(String desc);
  }
  

  public interface TimestampStep {
    BuildStep timestamp(String timestamp);
  }
  

  public interface BuildStep {
    Suggestion build();
    BuildStep id(String id);
    BuildStep userSuggestionsId(String userSuggestionsId);
    BuildStep categorySuggestionsId(String categorySuggestionsId);
    BuildStep weatherSuggestionsId(String weatherSuggestionsId);
  }
  

  public static class Builder implements NameStep, DescStep, TimestampStep, BuildStep {
    private String id;
    private String name;
    private String desc;
    private String timestamp;
    private String userSuggestionsId;
    private String categorySuggestionsId;
    private String weatherSuggestionsId;
    @Override
     public Suggestion build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Suggestion(
          id,
          name,
          desc,
          timestamp,
          userSuggestionsId,
          categorySuggestionsId,
          weatherSuggestionsId);
    }
    
    @Override
     public DescStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public TimestampStep desc(String desc) {
        Objects.requireNonNull(desc);
        this.desc = desc;
        return this;
    }
    
    @Override
     public BuildStep timestamp(String timestamp) {
        Objects.requireNonNull(timestamp);
        this.timestamp = timestamp;
        return this;
    }
    
    @Override
     public BuildStep userSuggestionsId(String userSuggestionsId) {
        this.userSuggestionsId = userSuggestionsId;
        return this;
    }
    
    @Override
     public BuildStep categorySuggestionsId(String categorySuggestionsId) {
        this.categorySuggestionsId = categorySuggestionsId;
        return this;
    }
    
    @Override
     public BuildStep weatherSuggestionsId(String weatherSuggestionsId) {
        this.weatherSuggestionsId = weatherSuggestionsId;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, String desc, String timestamp, String userSuggestionsId, String categorySuggestionsId, String weatherSuggestionsId) {
      super.id(id);
      super.name(name)
        .desc(desc)
        .timestamp(timestamp)
        .userSuggestionsId(userSuggestionsId)
        .categorySuggestionsId(categorySuggestionsId)
        .weatherSuggestionsId(weatherSuggestionsId);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder desc(String desc) {
      return (CopyOfBuilder) super.desc(desc);
    }
    
    @Override
     public CopyOfBuilder timestamp(String timestamp) {
      return (CopyOfBuilder) super.timestamp(timestamp);
    }
    
    @Override
     public CopyOfBuilder userSuggestionsId(String userSuggestionsId) {
      return (CopyOfBuilder) super.userSuggestionsId(userSuggestionsId);
    }
    
    @Override
     public CopyOfBuilder categorySuggestionsId(String categorySuggestionsId) {
      return (CopyOfBuilder) super.categorySuggestionsId(categorySuggestionsId);
    }
    
    @Override
     public CopyOfBuilder weatherSuggestionsId(String weatherSuggestionsId) {
      return (CopyOfBuilder) super.weatherSuggestionsId(weatherSuggestionsId);
    }
  }
  
}
