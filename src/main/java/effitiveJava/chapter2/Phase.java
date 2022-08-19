package effitiveJava.chapter2;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.automaton.FiniteStringsIterator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author violet
 */
@Slf4j
public enum Phase {
    SOLID, LIQUID, GAS;

    public enum Translation {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);
        private final Phase from;
        private final Phase to;

        Translation(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        private static final Map<Phase, Map<Phase, Translation>> m =
                Stream.of(values()).collect(groupingBy(t -> t.from,
                        () -> new EnumMap<>(Phase.class),
                        toMap(t -> t.to, t -> t,
                                (x, y) -> y, () -> new EnumMap<>(Phase.class))));

        public static Translation from(Phase from, Phase to) {
            return m.get(from).get(to);
        }
    }

    public static void main(String[] args) {
        for (Map.Entry<Phase, Map<Phase, Translation>> entry : Translation.m.entrySet()) {
            log.info("key:{}", entry.getKey());
            for (Map.Entry<Phase, Translation> e : entry.getValue().entrySet()) {
                log.info("key:{}, value:{}", e.getKey(), e.getValue());
            }
        }

        LocalDateTime localDateTime = LocalDateTime.now().minusSeconds(60);

        LocalDateTime now = LocalDateTime.now();

        log.info("{}", Duration.between(localDateTime,now).toSeconds());


    }
}
