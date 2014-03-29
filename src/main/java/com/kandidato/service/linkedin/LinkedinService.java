package com.kandidato.service.linkedin;

import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

public class LinkedinService {

    private static final Logger LOG = Logger.getLogger(LinkedinService.class.getName());

    @Inject
    private LinkedIn linkedinApi;

    @RequestMapping(value = "/network", method = RequestMethod.GET)
    @ResponseBody
    public String network(HttpServletResponse response) throws URISyntaxException, IOException {
        List<LinkedInProfile> connections = linkedinApi.connectionOperations().getConnections();

        StringBuilder builder = new StringBuilder("<html><body><ul>");

        for (LinkedInProfile person : connections) {
            builder.append("<li>" + person + "</li>");
        }
        builder.append("</ul></body></html>");
        response.setHeader("Content-Type", "text/html");
        return builder.toString();
    }
}
