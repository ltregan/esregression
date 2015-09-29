package ltregan;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import org.junit.Test;

/**
 * Created by loic on 9/27/2015.
 */
public class AppTest {

    @Test
    public void testApp() throws Exception{

        String json = "{  id: \"33\", name: \"loic\"}";
        NodeBuilder builder = new NodeBuilder();
        builder.settings().put("path.home", "c:/temp"+System.currentTimeMillis());
        //  builder.settings().put("index.store.type","memory");
        Node node = builder.local(true).node();
        Client client = node.client();

        IndexResponse r = client
                .prepareIndex("lookbooks", "lookbook")
                .setId( "33" )
                .setSource( json )
                .execute()
                .actionGet();

        GetResponse response = client.prepareGet("lookbooks", "lookbook", "33")
                .execute()
                .actionGet();
        System.out.println(response.getSource());
    }


}
