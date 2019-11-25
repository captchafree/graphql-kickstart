package graphqlkickstart;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import schemabuilder.processor.GraphQLSchemaBuilder;
import schemabuilder.processor.schema.SchemaParser;
import schemabuilder.processor.wiring.GraphQLWiringBuilder;
import schemabuilder.processor.wiring.GraphQLWiringBuilderOptions;

@Component
public class GraphQLProvider {

    /** All GraphQL annotated files must be in this package to be picked up */
    private static final String BASE_PACKAGE = "graphqlkickstart";

    /** The directory to search for graphql schema files */
    private static final String SCHEMA_DIRECTORY = "graphql_schema";

    /**
     * The file extension to look for. Files ending in this extension are treated as graphql files
     * containing sdl content.
     */
    private static final String SCHEMA_FILE_EXTENSION = "graphqls";

    private GraphQL graphQLWithoutTracing;

    @Bean
    public GraphQL getGraphQL() {
        return this.graphQLWithoutTracing;
    }

    /**
     *  Parse the GraphQL SDL files and map them to their respective DataFetchers
     */
    @PostConstruct
    public void init() throws Exception {
        SchemaParser schemaParser = new SchemaParser(SCHEMA_DIRECTORY, SCHEMA_FILE_EXTENSION);

        GraphQLWiringBuilderOptions options =
                new GraphQLWiringBuilderOptions.Builder()
                        .basePackage(BASE_PACKAGE)
                        .shouldPrintHierarchy(true)
                        .build();

        GraphQLWiringBuilder builder = new GraphQLWiringBuilder(options);


        GraphQLSchema graphQLSchema = new GraphQLSchemaBuilder(schemaParser, builder).getSchema();
        this.graphQLWithoutTracing = GraphQL.newGraphQL(graphQLSchema).build();
    }
}
