package com.application.example.repository.graphQL;

import com.application.example.entity.Student;

import com.application.example.request.UpdateStudentRequest;
import com.application.example.response.StudentResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository{
    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private ObjectMapper objectMapper;
    String INDEX = "spring_learning";
    String TYPE = "_doc";

    public Student save(Student student){
        String json = "";
        try {
            json = objectMapper.writeValueAsString(student);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        IndexRequest indexRequest = new IndexRequest().type(TYPE).index(INDEX).source(json, XContentType.JSON);//new IndexRequest(INDEX, TYPE).source(json);
        System.out.println(indexRequest);
        try {
            client.index(indexRequest,RequestOptions.DEFAULT);
        }
        catch (IOException e){
            System.out.println("Student not created successfully");
        }
        return student;
    }

    public Student saveAndFlush(UpdateStudentRequest updateStudentRequest) {
        Map<String, Object> dataMap = objectMapper.convertValue(updateStudentRequest, Map.class);
        UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, updateStudentRequest.getId());
        updateRequest.doc(dataMap);
        try {
            client.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Student(updateStudentRequest);
    }

    public Boolean deleteById (String id) {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE,id);
        try {
            client.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private List<StudentResponse> getSearchResult(SearchResponse response){
        SearchHit[] searchHit = response.getHits().getHits();

        List<StudentResponse> studentDocuments = new ArrayList<>();

        for (SearchHit hit : searchHit){
            studentDocuments
                    .add(objectMapper
                            .convertValue(hit
                                    .getSourceAsMap(), StudentResponse.class));
        }

        return studentDocuments;
    }
    public List<StudentResponse> getStudents() {
        SearchRequest searchRequest = buildSearchRequest(INDEX,TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getSearchResult(searchResponse);
    }

    private SearchRequest buildSearchRequest(String index, String type) {
        SearchRequest searchRequest = new SearchRequest(INDEX,TYPE);
        searchRequest.indices(INDEX);
        searchRequest.types(TYPE);
        return searchRequest;
    }

    public Student findById(String id){
        GetRequest getRequest = new GetRequest(INDEX, TYPE, id);

        GetResponse getResponse = null;
        try {
            getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> resultMap = getResponse.getSource();
        return objectMapper.convertValue(resultMap,Student.class);
    }
}
