package enums;

public enum Services {
    PETSTORE_SWAGGER("https://petstore.swagger.io"),
    IPAPI("http://api.ipapi.com"),
    YANDEX("https://yandex.ru/");

    private final String services;

    Services(String services) {
        this.services = services;
    }

    public String getServices() {
        return services;
    }
}
