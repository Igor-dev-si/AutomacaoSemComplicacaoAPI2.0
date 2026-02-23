package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.RestUtils;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        tags = "", //exemplo, as tags usa @ la na feature, e dá pra usar tags de 2 features diferentes
        //desde que esteja no mesmo caminho
        glue = "steps", //o glue é onde direciona onde está nossos steps, pra não criar repetido
        plugin = {"json:target/reports/cucumberReports.json", "pretty"},
        snippets = CucumberOptions.SnippetType.CAMELCASE //java usa camelcase, camelcase começa com letra minuscula e não tem espaço
        //Tudo isso pode ser colocado numa linha só, um do lado do outro

)
public class RunnerTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        RestUtils.setBaseURI("http://localhost:8181/");
    }
}
