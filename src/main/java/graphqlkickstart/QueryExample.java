package graphqlkickstart;

import graphql.schema.DataFetcher;
import schemabuilder.annotations.GraphQLDataFetcher;
import schemabuilder.annotations.GraphQLTypeConfiguration;

@GraphQLTypeConfiguration("Query")
public class QueryExample {

    @GraphQLDataFetcher
    private DataFetcher test() {
        return env -> "Hello, World!";
    }
}
