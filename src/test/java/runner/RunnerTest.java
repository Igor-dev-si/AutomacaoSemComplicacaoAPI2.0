package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@login", //exemplo, as tags usa @ la na feature, e dá pra usar tags de 2 features diferentes
        //desde que esteja no mesmo caminho
        glue = "steps", //o glue é onde direciona onde está nossos steps, pra não criar repetido
        plugin = {"json:target/reports/cucumberReports.json", "pretty"},
        snippets = CucumberOptions.SnippetType.CAMELCASE //java usa camelcase, camelcase começa com letrta minuscula e não tem espaço
        //Tudo isso pode ser colocado numa linha só, um do lado do outro

)
public class RunnerTest {

}
