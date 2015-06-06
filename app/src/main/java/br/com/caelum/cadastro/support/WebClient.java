package br.com.caelum.cadastro.support;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by android5044 on 30/05/15.
 */
public class WebClient {

    private static final String URL = "http://www.caelum.com.br/mobile";

    public String post(String json) {

        String jsonDeResposta = null;

        HttpPost post=new HttpPost(URL);
        try {
            post.setHeader("Content-type", "application/json");
            post.setHeader("Accept", "application/json");
            post.setEntity(new StringEntity(json));

            DefaultHttpClient client=new DefaultHttpClient();
            HttpResponse response = client.execute(post);
            jsonDeResposta = EntityUtils.toString(response.getEntity());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonDeResposta;
    }

}
