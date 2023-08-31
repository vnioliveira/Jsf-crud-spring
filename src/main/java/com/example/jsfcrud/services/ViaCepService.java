package com.example.jsfcrud.services;

import com.example.jsfcrud.entities.Endereco;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Bean;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.IOException;

@ManagedBean
@ViewScoped
@RequiredArgsConstructor
@Getter
public class ViaCepService {

    public Endereco getEndereco(String cep) throws IOException {

        Endereco endereco = null;
        HttpGet request = new HttpGet("https://viacep.com.br/ws/" + cep + "/json/");

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                Gson gson = new Gson();
                endereco = gson.fromJson(result, Endereco.class);
            }

        }

        return endereco;
    }

}

