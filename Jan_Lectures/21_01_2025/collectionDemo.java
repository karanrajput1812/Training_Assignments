import java.util.*;

public class collectionDemo {
	public static void main(String args[]) {
		// System.out.println("- -");
		Vector v1 = new Vector(2);

		v1.add("Hello");
		v1.add(2222);
		v1.add(new Date());
		v1.add(new Thread());
		v1.add(324.33);
		Enumeration e = v1.elements();

		while (e.hasMoreElements()) {
			System.out.println(e.nextElement());
		}

		System.out.println("-------------------------");

		// HashSet obj = new HashSet();			// Random order
		// TreeSet obj = new TreeSet();    		// Sorted order
		// LinkedHashSet obj = new LinkedHashSet();		// Insertion order

		// ArrayList obj = new ArrayList();		// Random order
		// LinkedList obj = new LinkedList();	// Insertion order

		// PriorityQueue obj = new PriorityQueue(); // Sorted order
		ArrayDeque obj = new ArrayDeque();		// Insertion order

		obj.add("111");
		obj.add("222");
		obj.add("333");
		obj.add("444");
		obj.add("333");

		obj.addFirst("000");
		obj.addLast("999");
		// obj.add(3, "787"); // Only supported in PriorityQueue and LinkedList not in ArrayDeque

		Iterator i = obj.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}

		System.out.println("-------------------------");

		// Hashtable mobj = new Hashtable();	// Random order and no null values
		HashMap mobj = new HashMap();		// Random order and one null key
		// TreeMap mobj = new TreeMap();		// Sorted order and no null values
		// LinkedHashMap mobj = new LinkedHashMap();	// Insertion order and one null key

		mobj.put("222", "Rakesh");
		mobj.put("333", "Mahesh");
		mobj.put("444", "Dinesh");
		mobj.put("111", "Rajesh");
		mobj.put("111", "Suresh");


		Set s = mobj.entrySet();
		Iterator i1 = s.iterator();

		while (i1.hasNext()) {
			Map.Entry me = (Map.Entry) i1.next();
			System.out.println("ID: " + me.getKey() + " Name: " + me.getValue());
			System.out.println();
		}
	}
}