package com.azure.search;

import com.azure.search.document.DocumentOp;
import com.azure.search.document.DocumentOps;
import com.azure.search.document.DocumentOpsResult;
import com.azure.search.exception.AzureSearchException;
import com.azure.search.query.SearchQuery;
import com.azure.search.query.SearchResult;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public final class AzureSearch implements AutoCloseable {

    private static final String MODIFY_DOCUMENTS_URL = "https://%s.search.windows.net/indexes/%s/docs/index?api-version=%s";
    private static final String SEARCH_DOCUMENTS_URL = "https://%s.search.windows.net/indexes/%s/docs?api-version=%s&%s";

    private final AzureSearchConfig config;
    private final CloseableHttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private AzureSearch(AzureSearchConfig config) {
        this.config = config;
        this.client = HttpClients.createDefault();
    }

    public static AzureSearch create(AzureSearchConfig config) {
        return new AzureSearch(config);
    }

    public DocumentOpsResult documentOps(String indexName, DocumentOp... documents) throws AzureSearchException {
        DocumentOps documentOps = documentOpsObject(documents);
        String requestJson = documentOpsJson(documentOps);
        HttpPost post = documentOpsRequest(indexName, requestJson);

        try (CloseableHttpResponse httpResponse = client.execute(post)) {
            String responseJson = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            return objectMapper.readValue(responseJson, DocumentOpsResult.class);
        } catch (IOException e) {
            throw new AzureSearchException(e);
        }
    }

    public SearchResult search(String indexName, SearchQuery query) throws AzureSearchException {
        HttpGet get = searchRequest(indexName, query.toQuery());

        try (CloseableHttpResponse httpResponse = client.execute(get)) {
            String responseJson = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            return objectMapper.readValue(responseJson, SearchResult.class);
        } catch (IOException e) {
            throw new AzureSearchException(e);
        }
    }

    private HttpGet searchRequest(String indexName, String searchParam) {
        HttpGet get = new HttpGet(searchUrl(indexName, searchParam));

        get.addHeader("Accept", ContentType.APPLICATION_JSON.toString());
        addApiKey(get);

        return get;
    }

    private HttpPost documentOpsRequest(String indexName, String modifyDocumentsBody) {
        HttpPost post = new HttpPost(modifyDocumentsUrl(indexName));

        post.setEntity(new StringEntity(modifyDocumentsBody, ContentType.APPLICATION_JSON));
        addApiKey(post);

        return post;
    }

    private String documentOpsJson(DocumentOps documentOps) throws AzureSearchException {
        try {
            return objectMapper.writeValueAsString(documentOps);
        } catch (IOException e) {
            throw new AzureSearchException(e);
        }
    }

    private void addApiKey(HttpRequestBase request) {
        request.addHeader("api-key", config.getAdminKey());
    }

    private String modifyDocumentsUrl(String indexName) {
        return String.format(MODIFY_DOCUMENTS_URL,
                config.getServiceName(),
                indexName, config.getApiVersion());
    }

    private String searchUrl(String indexName, String queryParams) {
        return String.format(SEARCH_DOCUMENTS_URL,
                config.getServiceName(),
                indexName, config.getApiVersion(), queryParams);
    }

    private DocumentOps documentOpsObject(DocumentOp[] documents) {
        DocumentOps documentOps = new DocumentOps();

        for (DocumentOp op : documents) {
            documentOps.getValue().add(op.getFields());
        }

        return documentOps;
    }

    @Override
    public void close() throws Exception {
        this.client.close();
    }
}

