package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ListUtils {
	private ListUtils() {
	}

	public static <T>List<T> set2List(Set<T> set)
	{
		List<T> result = new ArrayList<T>();
		for (T t : set)
		{
			result.add(t);
		}
		return result;
	}
}
