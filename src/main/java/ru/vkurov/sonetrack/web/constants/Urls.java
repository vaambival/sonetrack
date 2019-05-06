package ru.vkurov.sonetrack.web.constants;

public interface Urls {
    String API ="api";
    String ROOT = API +"/" + Api.VERSION;

    interface Authorization {
        String PART = "session";
        String FULL = ROOT + "/" + PART;
    }

    interface Problem {
        String PART = "task";
        String FULL = ROOT + "/" + PART;

        interface ById {
            String ID = "{id}";
            String PREFIX = "{prefix}";
            String FULL = "/" + PREFIX + "/" + ID;
        }

        interface Comment {
            String PART = "comment";
            String FULL = Problem.FULL + ById.FULL + "/" + PART;
        }
    }

    interface User {
        String PART = "user";
        String FULL = ROOT + "/" + PART;
    }

    interface Request {
        String PART = "request";
        String FULL = ROOT + "/" + PART;

        interface ById {
            String ID = "{id}";
            String FULL = "/" + ID;
        }
    }
}
