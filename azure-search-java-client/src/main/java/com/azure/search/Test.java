package com.azure.search;

import com.azure.search.query.SearchQuery;
import com.azure.search.query.SearchResult;
import com.azure.search.query.filter.Filters;

public final class Test {

    public static void main(String[] args){
        AzureSearchConfig config = new AzureSearchConfig("recrutory")
                .addAdminKey("D635E19285FB30560A2086FD3E8F589F");

        try(AzureSearch client = AzureSearch.create(config)) {
            /*client.documentOps("test",
                    new UploadDocument("6")
                            .addField("abc", "f")
                            .addField("text", "abcdef")
                            .addField("num", 5));*/

            SearchResult result = client.search("test", SearchQuery.forTerm("*")
                            .filter(Filters.eq("num", 5))
                            .includeCount()
            );

            System.out.println(result.getCount());
            System.out.println("=======");
            result.getValue().forEach(hit -> {
                System.out.println(hit.getStringField("id"));
                System.out.println(hit.getStringField("abc"));
                System.out.println(hit.getStringField("text"));
                System.out.println(hit.getIntegerField("num"));

                System.out.println("=======");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




