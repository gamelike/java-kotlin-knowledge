package effitiveJava.chapter2.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

@Setter
@Getter
@RequiredArgsConstructor
public class Tile {
    final int i;
    final Tile j;
    final int[] arr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return i == tile.i && Objects.equals(j, tile.j) && Arrays.equals(arr, tile.arr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(i, j);
        result = 31 * result + Arrays.hashCode(arr);
        return result;
    }
}
