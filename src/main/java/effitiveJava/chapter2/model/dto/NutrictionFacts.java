package effitiveJava.chapter2.model.dto;

import lombok.Getter;

/**
 * builder pattern
 */
@Getter
public class NutrictionFacts {

    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    private NutrictionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.serving;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static class Builder {
        // required parameters
        private final int servingSize;
        private final int serving;

        // Optional parameters - initialized to default values
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int serving){
            this.servingSize = servingSize;
            this.serving = serving;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutrictionFacts build(Builder builder) {
            return new NutrictionFacts(this);
        }
    }

}
