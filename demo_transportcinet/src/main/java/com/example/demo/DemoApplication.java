package com.example.demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")

@ComponentScan // Using a root package also allows the @ComponentScan annotation to be used without needing to specify a basePackage attribute
public class DemoApplication /*implements CommandLineRunner*/{

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=SpringApplication.run(DemoApplication.class, args);
		ctx.getBean("testClass",TestClass.class).issueBook();
		
		
		String expression="if((null!=ksuEntity) &&(null!=ksuEntity.getChildEntity('$$entityId$$'))){if((null!=ksuEntity.getChildEntity('$$entityId$$').getKsuEntityAttributesMap())){if((null!=ksuEntity.getChildEntity('$$entityId$$').getKsuEntityAttributesMap().get('KCICategory')) &&(ksuEntity.getChildEntity('$$entityId$$').getKsuEntityAttributesMap().get('KCICategory').equalsIgnoreCase('BTM Handset'))){if(null!=ksuEntity.getChildEntity('$$entityId$$').getKsuEntityAttributesMap().get('Action') &&(ksuEntity.getChildEntity('$$entityId$$').getKsuEntityAttributesMap().get('Action').equalsIgnoreCase('Add'))){if((null!=ksuEntity.getChildEntity('$$entityId$$').getKsuEntityAttributesMap().get('ProvisionInstruction')) &&!(ksuEntity.getChildEntity('$$entityId$$').getKsuEntityAttributesMap().get('ProvisionInstruction').equalsIgnoreCase(\"\"))){if((null!=ksuEntity.getChildEntity('$$entityId$$').getKsuEntityAttributesMap().get('CustomerCommittedDate')) &&!(\"\".equalsIgnoreCase(ksuEntity.getChildEntity('$$entityId$$').getKsuEntityAttributesMap().get('CustomerCommittedDate')))){attributeValue=ksuEntity.getChildEntity('$$entityId$$').getKsuEntityAttributesMap().get('CustomerCommittedDate');}else{\tattributeValue=ksuEntity.getChildEntity('$$entityId$$').getKsuEntityAttributesMap().get('FormattedCCPCAD');}}else{attributeValue=ksuEntity.getChildEntity('$$entityId$$').getKsuEntityAttributesMap().get('FormattedCCPCAD');}}}}}\n";

		System.out.println(expression.replaceAll("$$entityId$$", "3-12ARRU"));

		 

		
	}

	//@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		TransportClient client =createClient();
		if(!isIndexRegistered("students", client))
		{
			createIndex("students","1","1",client);
		}
		else
		{
			System.out.println("Index not registered created");
		}
		if(bulkInsert("students","student",client))
			System.out.println("Bulk insert done");
		
	}
	
	
	
	
	
	public TransportClient createClient() throws UnknownHostException {
		
		  TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
		            .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

		
		/*Settings settings = Settings.builder()
				  .put( "cluster.name", clusterName )
				  .put( "client.transport.ignore_cluster_name", true )
				  .put( "client.transport.sniff", true )
				  .build();
				
				// create connection
				client = new PreBuiltTransportClient( settings ); 
				client.addTransportAddress( new TransportAddress( InetAddress.getByName( clusterIp ), clusterPort) );*/
		  return client;
				
	}
	
	

	public boolean createIndex( String indexName, String numberOfShards, String numberOfReplicas,TransportClient client ) {
		CreateIndexResponse createIndexResponse = 
			client.admin().indices().prepareCreate( indexName )
        	.setSettings( Settings.builder()             
                .put("index.number_of_shards", numberOfShards ) 
                .put("index.number_of_replicas", numberOfReplicas )
        	)
        	.get(); 
				
		if( createIndexResponse.isAcknowledged() ) {
			System.out.println("Created Index with " + numberOfShards + " Shard(s) and " + numberOfReplicas + " Replica(s)!");
			return true;
		}
		
		return false;				
	}

	public boolean isIndexRegistered( String indexName ,TransportClient client) {
		// check if index already exists
		final IndicesExistsResponse ieResponse = client
			    .admin()
			    .indices()
			    .prepareExists( indexName )
			    .get( TimeValue.timeValueSeconds( 1 ) );
			
		// index not there
		if ( !ieResponse.isExists() ) {
			return false;
		}
		
		System.out.println( "Index already created!" );
		return true;
	}
	public boolean bulkInsert( String indexName, String indexType , TransportClient client) throws IOException { 
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		
		// either use client#prepare, or use Requests# to directly build index/delete requests
		bulkRequest.setRefreshPolicy( RefreshPolicy.IMMEDIATE ).add( 
			client.prepareIndex( indexName, indexType, null )
		        .setSource( XContentFactory.jsonBuilder()
	                .startObject()
	                    .field( "name", "Mark Twain" )
	                    .field( "age", 75 )
	                .endObject()
	    ));

		bulkRequest.setRefreshPolicy( RefreshPolicy.IMMEDIATE ).add( 
			client.prepareIndex( indexName, indexType, null )
		        .setSource( XContentFactory.jsonBuilder()
	                .startObject()
	                    .field( "name", "Tom Saywer" )
	                    .field( "age", 12 )
	                .endObject()
		));
		
		bulkRequest.setRefreshPolicy( RefreshPolicy.IMMEDIATE ).add( 
			client.prepareIndex( indexName, indexType, null )
		        .setSource( XContentFactory.jsonBuilder()
	                .startObject()
	                    .field( "name", "John Doe" )
	                    .field( "age", 20 )
	                .endObject()
		));
		
		bulkRequest.setRefreshPolicy( RefreshPolicy.IMMEDIATE ).add( 
			client.prepareIndex( indexName, indexType, null )
		        .setSource( XContentFactory.jsonBuilder()
	                .startObject()
	                    .field( "name", "Peter Pan" )
	                    .field( "age", 15 )
	                .endObject()
		));
		
		bulkRequest.setRefreshPolicy( RefreshPolicy.IMMEDIATE ).add( 
			client.prepareIndex( indexName, indexType, null )
		        .setSource( XContentFactory.jsonBuilder()
	                .startObject()
	                    .field( "name", "Johnnie Walker" )
	                    .field( "age", 37 )
	                .endObject()
		));

		BulkResponse bulkResponse = bulkRequest.get();
		if ( bulkResponse.hasFailures() ) {
		    // process failures by iterating through each bulk response item
			System.out.println( "Bulk insert failed!" );
			return false;
		}
		
		return true;
	}
	
	
	
	
	
	//http://localhost:9200/students/student/_search
	//http://localhost:9200/students/student/rFWcyWwB6tjRAxLyYMn6
	
	//curl 'http://localhost:9200/students/student/_search?q=Johnnie Walker&pretty=true'
	
	/*
	 * 
	 *https://vitalflux.com/elasticsearch-create-query-delete-index-java-example/

https://stackoverflow.com/questions/43342333/correct-use-of-transportclient-for-elasticsearch

http://teknosrc.com/execute-raw-elasticsearch-query-using-transport-client-java-api/

https://tutorial-academy.com/elasticsearch-6-create-index-bulk-insert-delete-java-api/ 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * https://mindmajix.com/elasticsearch/curl-syntax-with-examples
	 * https://mindmajix.com/elasticsearch/crud-operations-and-sorting-documents
	 * 
	 * 
	 * http://www.elasticsearchtutorial.com/elasticsearch-in-5-minutes.html
	 * 
	 * 
	 */
	 

		
		
		

}
