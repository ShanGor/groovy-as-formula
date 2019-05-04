package tech.comfortheart.demo.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*
import tech.comfortheart.demo.service.JwtGeneratorService
import tech.comfortheart.utils.Timer
import javax.script.Compilable
import javax.script.CompiledScript
import javax.script.ScriptEngineManager
import javax.script.SimpleBindings

@RestController
class TestController {
    val logger = LoggerFactory.getLogger(TestController::class.java)

    @Autowired
    lateinit var jwtGeneratorService: JwtGeneratorService

    @Autowired
    @Qualifier("groovyCompiledFormulas")
    lateinit var groovyFormulas: Map<String, CompiledScript>



    @RequestMapping("/api/test")
    fun test(): String {
        return "hello world"
    }

    @RequestMapping("/jwt")
    fun generateJwt(): String {
        return jwtGeneratorService.generateJwt();
    }

    @PostMapping("/calc/{script}/{var}")
    fun calculate(@PathVariable("script") scriptName: String,
                  @PathVariable("var") variable: String,
                  @RequestBody params: Map<String, Any>): Map<String, Any> {
        val timer = Timer()
        val yourScript = groovyFormulas.getValue(scriptName)

        val bindings = SimpleBindings()
        bindings.putAll(params)
        yourScript.eval(bindings)

        logger.info("Calculation done in ${timer.elapsedMillisecs()} milliseconds!")
        val map = LinkedHashMap<String, Any>()
        map.put(variable, bindings.get(variable)!!)
        return map
    }
}