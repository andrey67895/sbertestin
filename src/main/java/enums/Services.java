package enums;

public enum Services {
    PETSTORE_SWAGGER("https://petstore.swagger.io"),
    IPAPI("http://api.ipapi.com");

    private final String services;

    Services(String services) {
        this.services = services;
    }

    public String getServices() {
        return services;
    }
}
