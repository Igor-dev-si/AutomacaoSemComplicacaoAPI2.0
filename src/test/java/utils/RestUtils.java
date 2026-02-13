package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class RestUtils {

    private static Response response;

    public static Response getResponse() {
        return response;
    }

    public static void setBaseURI(String uri){
        RestAssured.baseURI = uri;
    }

    public static String getBaseURI(){
        return RestAssured.baseURI;
    }

    public static Response post(Object json, ContentType contentType, String endpoint){
        //Fazendo do jeito acima, deixa livre pra receber(abaixo os metodos) o tipo que eu quiser, sem ser um tipo especifico
        //Deixa mais util, pra mais coisas
        return response = RestAssured.given()
                .contentType(contentType)
                .log().all()
                .body(json)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
    public static Response get(Map<String, String> header, String endpoint) {
        return RestAssured.given()
                .relaxedHTTPSValidation()
                //Essa estrutura do RestAssured acima, faz a validação do certificado
                //Pra ver se é seguro ou não, o que for HTTPS não precisa ser validado, meio que agt assume a responsabilidade se vai estar seguro ou não
                //Geralmente não faz isso em produção, principalmente se for api
                //pesquisar melhor depois sobre
                .headers(header)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}
