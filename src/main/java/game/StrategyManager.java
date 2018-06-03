package game;

import game.move.MoveStrategy;
import game.move.MoveStrategyFromJSScriptLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StrategyManager {

    private final MoveStrategyFromJSScriptLoader strategyLoader;

    private List<MoveStrategy> loadedStrategies;

    public StrategyManager(MoveStrategyFromJSScriptLoader strategyLoader) {
        this.strategyLoader = strategyLoader;
        loadedStrategies = new ArrayList<>();
    }


    public void loadFromDirectory(File directory) {
        File[] scriptFiles = directory.listFiles(StrategyManager::scriptFileFilter);

        loadedStrategies = Stream.of(scriptFiles).map(file -> strategyLoader.load(file))
                .filter(optional -> optional.isPresent())
                .map(optional -> optional.get())
                .collect(Collectors.toList());

    }

    private static boolean scriptFileFilter(File dir, String name) {
        return name.endsWith("Strategy.js");
    }

    public List<MoveStrategy> getLoadedStrategies() {
        return this.loadedStrategies;
    }

}
