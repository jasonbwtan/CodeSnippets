package com.jason.guava_examples;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class GuavaExamples {
	public static void main(String[] args) {
		/* test data */
		ArrayList<String> testList = new ArrayList<String>();
		HashSet<String> testSet = new HashSet<String>();
		HashMap<String, String> testMap = new HashMap<String, String>();

		/* Removes all nulls from list */
		List<String> values = Lists.newArrayList("a", null, "b", "c", null, "d");
		Iterable<String> withoutNulls = Iterables.filter(values, Predicates.notNull());
		out.println(withoutNulls);

		/*
		 * Immutable list from elements. When you don't expect to modify a collection, or expect a
		 * collection to remain constant, it's a good practice to defensively copy itinto an immutable
		 * collection. Each of the Guava immutable collection implementationsrejects null values
		 */
		ImmutableList<String> immutableList1 = ImmutableList.of("a", "b", "c");
		ImmutableSet<String> immutableSet1 = ImmutableSet.of("a", "b", "c");
		ImmutableMap<String, String> imuttableMap1 = ImmutableMap.copyOf(testMap);

		/* Immutable list from mutable list */
		List<String> muttableList = Lists.newArrayList();
		ImmutableList<String> immutableList2 = ImmutableList.copyOf(muttableList);

		Set<String> muttableSet = Sets.newHashSet();
		ImmutableSet<String> immutableSet2 = ImmutableSet.copyOf(muttableSet);

		Map<String, String> muttableMap = Maps.newHashMap();
		ImmutableMap<String, String> imuttableMap2 = ImmutableMap.copyOf(muttableMap);

		/* Check a list for a predicate */
		List<Integer> evenNumbers = Lists.newArrayList(2, 6, 8, 10, 34, 90);
		Predicate<Integer> acceptEven = new Predicate<Integer>() {
			@Override
			public boolean apply(Integer number) {
				return (number % 2) == 0;
			}
		};
		System.out.println(Iterables.all(evenNumbers, acceptEven));

		/* MultiMap examples */
		Multimap<String, String> myMultimap = ArrayListMultimap.create();

		// Adding some key/value
		myMultimap.put("Fruits", "Bannana");
		myMultimap.put("Fruits", "Apple");
		myMultimap.put("Fruits", "Pear");
		myMultimap.put("Vegetables", "Carrot");

		// Getting the size
		int size = myMultimap.size();
		System.out.println(size); // 4

		// Getting values
		Collection<String> fruits = myMultimap.get("Fruits");
		System.out.println(fruits); // [Bannana, Apple, Pear]

		Collection<String> vegetables = myMultimap.get("Vegetables");
		System.out.println(vegetables); // [Carrot]

		// Iterating over entire Mutlimap
		for (String value : myMultimap.values()) {
			System.out.println(value);
		}

		// Removing a single value
		myMultimap.remove("Fruits", "Pear");
		System.out.println(myMultimap.get("Fruits")); // [Bannana, Pear]

		// Remove all values for a key
		myMultimap.removeAll("Fruits");
		System.out.println(myMultimap.get("Fruits")); // [] (Empty Collection!)
		
		/*BiMap Example*/
		BiMap<String,String> britishToAmerican = HashBiMap.create();
		 
		// Initialise and use just like a normal map
		britishToAmerican.put("aubergine","egglant");
		britishToAmerican.put("courgette","zucchini");
		britishToAmerican.put("jam","jelly");
		 
		System.out.println(britishToAmerican.get("aubergine")); // eggplant
		 
		BiMap<String,String> americanToBritish = britishToAmerican.inverse();
		 
		System.out.println(americanToBritish.get("eggplant")); // aubergine
		System.out.println(americanToBritish.get("zucchini")); // courgette
	}
}
