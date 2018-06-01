package game.move;

import jdk.nashorn.api.scripting.ScriptUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MoveStrategyFromJSFile {

    private static final String ENGINE_NAME = "nashorn";

    private static final String VAR_STRATEGY = "strategy";

    public MoveStrategy load(File file) throws FileNotFoundException, ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        engine.eval(new FileReader(file));

        MoveStrategy strategy = (MoveStrategy) ScriptUtils.convert(engine.eval(VAR_STRATEGY), MoveStrategy.class);

        return strategy;
    }

}
