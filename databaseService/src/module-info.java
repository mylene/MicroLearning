module nl.atos.devlab.microlearning.databaseservice {
    requires cdi.api;
    requires io.helidon.common;
    requires io.helidon.microprofile.server;

    requires java.ws.rs;
    requires javax.inject;
    requires java.json;
    requires java.logging;
    requires microprofile.config.api;

    exports nl.atos.devlab.microlearning.databaseservice;
}