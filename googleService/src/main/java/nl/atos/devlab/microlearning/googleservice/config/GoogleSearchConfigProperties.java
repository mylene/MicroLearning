package nl.atos.devlab.microlearning.googleservice.config;

import javax.enterprise.context.ApplicationScoped;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;

@ApplicationScoped
@ConfigBundle("google")
public class GoogleSearchConfigProperties {

    private String apiKey;

    private String cx;

    public String getApiKey() {
        return this.apiKey;
    }

    public String getCx() {
        return this.cx;
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    public void setCx(final String cx) {
        this.cx = cx;
    }
}
