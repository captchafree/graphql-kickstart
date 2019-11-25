package graphqlkickstart;

import graphql.GraphQL;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class GraphQLController {

    @Autowired private GraphQL graphql;

    @PostMapping(
            value = "/graphql",
            headers = "Content-Type=application/json"
    )
    @ResponseBody
    public Object executeOperation(@RequestBody Map body) {
        return graphql.execute((String) body.get("query")).toSpecification();
    }
}
