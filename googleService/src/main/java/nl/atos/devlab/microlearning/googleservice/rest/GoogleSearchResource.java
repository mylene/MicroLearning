package nl.atos.devlab.microlearning.googleservice.rest;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Search;
import com.kumuluz.ee.logs.Log4j2Logger;
import com.kumuluz.ee.logs.Logger;

import nl.atos.devlab.microlearning.googleservice.config.GoogleSearchConfigProperties;

@Path(value = "/api")
public class GoogleSearchResource {

    @Inject
    private GoogleSearchConfigProperties googleSearchConfigProperties;

    private static final Logger LOGGER = new Log4j2Logger().getLogger("google-search-log-config");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/search/{searchKey}")
    public List<Object> search(@PathParam("searchKey") final String searchKey) {

        try {

            final Customsearch search = new Customsearch(new NetHttpTransport(), new JacksonFactory(), null);
            final Customsearch.Cse.List searchList = search.cse().list(searchKey);
            searchList.setKey(googleSearchConfigProperties.getApiKey());
            searchList.setCx(googleSearchConfigProperties.getCx());

            final Search searchResult = searchList.execute();

            final JSONArray jsonArray = new JSONArray();

            searchResult.getItems().forEach(item -> {

                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", item.getTitle());
                jsonObject.put("link", item.getLink());
                jsonObject.put("snippet", item.getSnippet());

                jsonArray.put(jsonObject);
            });

            return jsonArray.toList();

        } catch (final IOException exception) {
            LOGGER.info(exception);
            return Collections.emptyList();
        }
    }
}
