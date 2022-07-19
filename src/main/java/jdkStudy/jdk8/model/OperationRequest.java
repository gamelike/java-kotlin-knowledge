package jdkStudy.jdk8.model;

import java.util.List;

/**
 * @author violet
 * @since 2022/7/19
 */
public record OperationRequest(List<String> ids,
                               String result,
                               List<String> influence) {
}
