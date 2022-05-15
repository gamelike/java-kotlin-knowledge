package jdk.jdk13;

import org.junit.Test;

/**
 * @author violet.
 */
public class Jdk13 {

    /**
     * <h1>switch expression</h1>
     * <p>feat: add yield.</p>
     */
    protected void switchExpression(int number) {
        int n = switch (number) {
            case 1 -> {
                yield 1;
            }
            case 2 -> {
                yield 2;
            }
            default -> {
                yield 4;
            }
        };
    }

    /**
     * <h1>document preview.</h1>
     * <p>text blocks</p>
     */
    protected void documentPreview() {
        // text block 1
        String html = """
                <html>
                    <body>
                        <p>hello world</p>
                    </body>
                </html>
                """;
        System.out.println(html);

        // SQL block 2
        String query = """
                SELECT * FROM TABLE
                WHERE ID = '1'
                ORDER BY ID DESC
                """;
        System.out.println(query);

        // error
//        String a = """""";
    }

}
