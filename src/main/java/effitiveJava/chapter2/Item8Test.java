package effitiveJava.chapter2;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.ref.Cleaner;

@Slf4j
public class Item8Test {
    @Test
    public void cleaner_test() {
        Item8 item8 = new Item8(2);
        log.info("main thread working first!");
        // maybe println logging
        System.gc();
    }

    @Test
    public void try_with_resources() {
        try (Item8 item8 = new Item8(12)) {
            log.info("main thread working");
        } catch (Exception e) {
            log.error("资源关闭错误!");
            throw new RuntimeException(e);
        }
    }
}

@Slf4j
class Item8 implements AutoCloseable {

    private static final Cleaner cleaner = Cleaner.create();

    // Resources that required cleaning. Must not ref Item8.
    private static class State implements Runnable {
        int numJunkPiles; // the number of junk piles in the class

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        // invoked by close method or cleaner.
        @Override
        public void run() {
            log.info("junk piles : {}", this.numJunkPiles);
            log.info("cleaning room!");
            numJunkPiles = 0;
            log.info("clean complete, number: {}", this.numJunkPiles);
        }
    }

    // this class, shared with Item.
    private final State state;

    // let class register. cleans garbage when it's eligible for gc.
    private final Cleaner.Cleanable cleanable;

    Item8(int numJunkPiles) {
        this.state = new State(numJunkPiles);
        this.cleanable = cleaner.register(this, this.state);
    }

    @Override
    public void close() throws Exception {
        this.cleanable.clean();
    }

}