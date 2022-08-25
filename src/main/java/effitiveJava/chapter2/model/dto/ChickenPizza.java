package effitiveJava.chapter2.model.dto;

import lombok.Getter;

import java.util.Objects;

/**
 * abstract class impl.
 */
@Getter
public class ChickenPizza extends Pizza{

    public enum Size {SMALL, MEDIUM, LARGE}
    private final Size size;

    ChickenPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        Pizza builder() {
            return new ChickenPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}

