package PlataformaFilmes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import maps.LoginMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.RestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class plataformaFilmesTest {

   // static String token;
    @Test
    public void validarLogin(){
        RestAssured.baseURI = "http://localhost:8080/";

        String json = "{" +
                "    \"email\":\"aluno@email.com\"," +
                "    \"senha\":\"123456\"" +
                "}";
//
//        Response response = RestAssured.given() //Metodo Response cria variavel response, para armazenar os dados que serão pegos pelos metodos abaixo
//                .contentType(ContentType.JSON)
//                .body(json)
//                .when()
//                .post("auth")
////                .then()
////                .extract()
////                .response();
//                .thenReturn(); //Substituí os 3 métodos acima.

        Response response = RestUtils.post(json, ContentType.JSON, "auth");//substitui a estrutura de cima toda. Fica mais pratico
        //Já que declarei a estrutura de cima, lá em baixo, como uma classe.

        assertEquals(200,response.statusCode());
        //String token = response.body().jsonPath().get("token"); //Duas formas de fazer
        String token = response.jsonPath().get("token");
        //System.out.println(token);

    }
    @BeforeAll
   // @Test   //Outra opção de estrutura pra rodar tudo que fizemos acima
    //Estrutura mais facil, limpa, simples e objetiva. Map é um metodo melhor
    //public void validarLoginMap(){
    public static void validarLoginMap(){ //Com o beforeall, tudo tem que ser static
        RestUtils.setBaseURI("http://localhost:8080/");
   //     RestAssured.baseURI = "http://localhost:8080/";
//        Map<String, String> map = new HashMap<>();
//        //esse new eu to usando pra instanciar
//        map.put("email", "aluno@email.com");
//        map.put("senha", "123456");

        //Com o cucumber ↓
        LoginMap.initLogin();

        Response response = RestUtils.post(LoginMap.getLogin(), ContentType.JSON, "auth");
        //Assim ficaria mais encapsulado ↑
        //Response response = RestUtils.post(map, ContentType.JSON, "auth");
        assertEquals(200,response.statusCode());
        LoginMap.token = response.jsonPath().get("token");
        //token = response.jsonPath().get("token");
        //Se eu declarar o token lá em cima como < String token > sem o maior ou menor,
        //vai virar uma variavel global, dai posso deixar só escrito token sem dizer que é uma string,
        //fica mais facil pra declarar dentro do código
        //exemplo: token = response.jsonPath().get("token"); ←
    }

    @Test
    public void validarConsultaCategorias(){
        Map<String, String> header = new HashMap<>();
//        header.put("Authorization", "Bearer " +token);
        header.put("Authorization", "Bearer " +LoginMap.token);

        Response response = RestUtils.get(header, "categorias");

        assertEquals(200, response.statusCode());
        System.out.println(response.jsonPath().get().toString());

        assertEquals("Terror", response.jsonPath().get("tipo[2]"));
        List<String> listTipo = response.jsonPath().getList("tipo");

        assertTrue(listTipo.contains("Terror"));//aqui valida se o retorno é verdadeiro


//        List<String> listTipo = new ArrayList<>();
//        listTipo.add("Aventura");
//        listTipo.add("Acao");
//        listTipo.add("Terror");
//        listTipo.add("Drama");
//        listTipo.add("Comedia");
//        listTipo.add("Sobrevivencia");
//        listTipo.add("Crime");
//        listTipo.add("Thriller");
//        //É uma forma de validação de tipos, mas não necessário
//
//        assertEquals(listTipo, response.jsonPath().get("tipo"));
//        List<Integer> listId = response.jsonPath().get();
//        assertEquals(8, listId.size());
        //Essa estrutura valida a quantidade de itens que quero, é opcional

    }


}
