package ru.vkurov.sonetrack.web.constants;

public interface Urls {
    String API ="api";
    String ROOT = API +"/" + Api.VERSION;

    interface Authorization {
        String PART = "session";
        String FULL = ROOT + "/" + PART;
    }
}
