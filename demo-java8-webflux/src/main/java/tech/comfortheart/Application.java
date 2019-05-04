package tech.comfortheart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.comfortheart.utils.ResourceUtil;
import tech.comfortheart.utils.Timer;

import javax.script.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class Application {
    Logger logger = LoggerFactory.getLogger(Application.class);

    @Value("${formula.groovy.files}")
    String[] groovyFiles;

    @Autowired
    ResourceUtil resourceUtil;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean("groovyCompiledFormulas")
    public Map<String, CompiledScript> getGroovyFormulaSources() throws ScriptException {
        Timer timer = new Timer();
        ScriptEngineManager factory = new ScriptEngineManager();
        Compilable groovyEngine = (Compilable)factory.getEngineByName("groovy");

        Map<String, CompiledScript> map = new ConcurrentHashMap<>();
        Bindings bindings = new SimpleBindings();
        for (String groovyFile: groovyFiles) {
            String source = resourceUtil.getResourceFromConfigServer(groovyFile.trim());
            CompiledScript script = groovyEngine.compile(source);
            script.eval(bindings);

            String fileName = groovyFile.split("\\.")[0];
            map.put(fileName, script);
        }

        logger.info("Groovy source initialized in " + timer.elapsedMillisecs() + " milliseconds");
        return map;
    }
}


