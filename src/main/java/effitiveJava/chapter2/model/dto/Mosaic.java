package effitiveJava.chapter2.model.dto;

import java.util.function.Supplier;

public class Mosaic {

    Mosaic create(Supplier<? extends Tile> tileFactory) {
        Tile tile = tileFactory.get();
        return new Mosaic();
    }

}
