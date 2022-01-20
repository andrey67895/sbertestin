package entities.ipapi.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.Country;
import lombok.Data;

import java.util.List;

@Data
public class IpapiResponseJson {
    private String ip;
    private String type;
    @JsonProperty(value = "continent_code")
    private String continentCode;
    @JsonProperty(value = "continent_name")
    private String continentName;
    @JsonProperty(value = "country_code")
    private Country countryCode;
    @JsonProperty(value = "country_name")
    private String countryName;
    @JsonProperty(value = "region_code")
    private String regionCode;
    @JsonProperty(value = "region_name")
    private String regionName;
    private String city;
    private String zip;
    private Double latitude;
    private Double longitude;
    private LocationJson location;

    @Data
    public static class LocationJson {
        @JsonProperty(value = "geoname_id")
        private Integer geonameId;
        private String capital;
        private List<LanguageJson> languages;
        @JsonProperty(value = "country_flag")
        private String countryFlag;
        @JsonProperty(value = "country_flag_emoji")
        private String countryFlagEmoji;
        @JsonProperty(value = "country_flag_emoji_unicode")
        private String countryFlagEmojiUnicode;
        @JsonProperty(value = "calling_code")
        private String callingCode;
        @JsonProperty(value = "is_eu")
        private Boolean isEu;
    }

    @Data
    public static class LanguageJson {
        private String code;
        private String name;
        @JsonProperty("native")
        private String nativeProperty;
    }
}
