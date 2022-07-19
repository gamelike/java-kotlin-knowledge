package jdkStudy.jdk8;

import jdkStudy.jdk8.model.Entity;
import jdkStudy.jdk8.model.OperationRequest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author gjd
 */
@SuppressWarnings("all")
public class OptionalStudy {
    private static Logger log = LoggerFactory.getLogger(OptionalStudy.class);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = null;
//      String str = sc.nextLine();
            if (str != null) {
                log.info(str.toLowerCase());
            }
            Optional
                    .ofNullable(str)  //封装
                    .ifPresent(s -> log.info(s.toUpperCase()));  // 对象不是null才会被接受.
            log.info(Optional
                    .ofNullable(str)
                    .orElse("VSW"));
            break;
        }

    }

    @Test
    public void stream_test() {
        OperationRequest operationRequest = new OperationRequest(List.of(UUID.randomUUID().toString().replace("-", ""),
                UUID.randomUUID().toString().replace("-", "")),
                "test", List.of("influence"));

        LocalDateTime now = LocalDateTime.now();
        List<Entity> collect = operationRequest.ids().stream().map(id -> new Entity().setId(id)
                .setAlarmIndexingId(id)
                .setCreateTime(now)
                .setEventTime(now)
                .setInfluence(operationRequest.influence())
                .setResult(operationRequest.result())).collect(Collectors.toList());

        System.out.println(collect);
    }
}
