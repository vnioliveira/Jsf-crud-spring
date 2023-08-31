package com.example.jsfcrud.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class Cep {

    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;

    public void buscaCep() {
        String cepFinal = this.cep.replaceAll("[^0-9]", "");
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://viacep.com.br/ws/" + cepFinal + "/json/");
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity);
                atualizaView(responseString);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao buscar cep: " + ex);
        }
    }

    public void atualizaView(String response) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(response);
            this.rua = node.get("logradouro").asText();
            this.bairro = node.get("bairro").asText();
            this.cidade = node.get("localidade").asText();
            this.estado = node.get("uf").asText();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
