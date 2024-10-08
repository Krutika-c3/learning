package com.application.example.exceptions;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class GraphQLExceptionHandler {

    @ExceptionHandler(GraphQLException.class)
    public ThrowableGraphQLError handleGraphQLException(GraphQLException e) {
        return new ThrowableGraphQLError(e);
    }

    @ExceptionHandler(RuntimeException.class)
    public ThrowableGraphQLError handleRuntimneException(RuntimeException e) {
        return new ThrowableGraphQLError(e, "Internal Server Error");
    }
}

