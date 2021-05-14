package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayListUtil
{
    // Found this at https://stackoverflow.com/questions/5283047/intersection-and-union-of-arraylists-in-java.
    public static <T> ArrayList<T> arrayListsUnion(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<>(set);
    }
}
