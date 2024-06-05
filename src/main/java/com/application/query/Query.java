package com.application.query;

import com.application.forGraphQL.SampleRequest;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

//@Component
public class Query implements GraphQLQueryResolver {

    public String test() {
        return "Say hello !";
    }

    public Integer sum(int a, int b) {
        return a + b;
    }

    public String fullName(SampleRequest sampleRequest) {
        return sampleRequest.getFirstName() + " " +  sampleRequest.getLastName();
    }
}
