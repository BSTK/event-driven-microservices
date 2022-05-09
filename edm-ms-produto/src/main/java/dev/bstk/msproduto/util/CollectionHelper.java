package dev.bstk.msproduto.util;

import java.util.Collection;
import java.util.Objects;

public class CollectionHelper {

    public static boolean isEmpty(final Collection<?> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }
}
