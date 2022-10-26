package elasticsearch.elasticsearch;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;



public class JavaElasticConnector {

	public static void main(String[] args) {
		
		RestHighLevelClient client = new RestHighLevelClient(
		RestClient.builder(new HttpHost(" http://elastic:ecp1234@172.25.37.112:9200/", 9200, "http")));
		
		SearchRequest searchRequest = new SearchRequest();
	    searchRequest.indices("courses");
	    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
	    searchSourceBuilder.query(QueryBuilders.matchAllQuery());
	    searchRequest.source(searchSourceBuilder);
	    Map<String, Object> map=null;
	     
	    try {
	        SearchResponse searchResponse = null;
	        searchResponse =client.search(searchRequest);
	        if (searchResponse.getHits().getTotalHits().value > 0) {
	            SearchHit[] searchHit = searchResponse.getHits().getHits();
	            for (SearchHit hit : searchHit) {
	                map = hit.getSourceAsMap();
	                  System.out.println("map:"+Arrays.toString(map.entrySet().toArray()));
	                    

	                  
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

}
