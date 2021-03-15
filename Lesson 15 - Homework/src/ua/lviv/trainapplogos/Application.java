/**
 *  Додати учасника клубу
    Додати тваринку до учасника клубу
    Видалити тваринку з учасника клубу
    Видалити учасника з клубу
    Видалити конкретну тваринку зі всіх власників
    Вивести на екран зооклуб
    Вийти з програми
 */

package ua.lviv.trainapplogos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		Map <Person, List<Pet>> map = new HashMap<>();
		//TreeMap <Person, List<Pet>> map = new TreeMap<>(); // because in task was mentioned 
															 // strict type Map <Person, List<Pet>> map = new HashMap<>(); 
															 // this variant was skipped 
		ZooClub zooclub = new ZooClub(map);
		//map = zooclub.getMap()
		
		Scanner sc = new Scanner(System.in).useDelimiter("\n");
		String name;
		String petname;
		boolean exists = false;
		
		PetsType[] petstype = PetsType.values();
		
		//*** Initialization start state of ZooClub ***
		//--- Add 1st member ---
		Person member1 = new Person("Johny", 31);
		List<Pet> petslist1 = new ArrayList<>();
		
		petslist1.add(new Pet(PetsType.CAT, "Tom"));
		petslist1.add(new Pet(PetsType.DOG, "Rex"));
		petslist1.add(new Pet(PetsType.MOUSE, "Jerry"));
		
		map.put(member1, petslist1);
		//--- 1st member ---
		
		//--- Add 2nd member ---
		Person member2 = new Person("Trevor", 24);
		List<Pet> petslist2 = new ArrayList<>();
		
		petslist2.add(new Pet(PetsType.FOX, "Alf"));
		petslist2.add(new Pet(PetsType.DOG, "Fluppy"));
		
		map.put(member2, petslist2);
		//--- 2nd member ---
		
		//--- Add 3rd member ---
		Person member3 = new Person("Stive", 47);
		List<Pet> petslist3 = new ArrayList<>();
		
		petslist3.add(new Pet(PetsType.DOG, "Ribbon"));
		petslist3.add(new Pet(PetsType.DOG, "Tilda"));
		
		map.put(member3, petslist3);
		//--- 3rd member ---
		//*** end of initialization
		
		while (true) {
			System.out.println("\n-----------------[Menu]-------------------");
			System.out.println("> Enter number to choose an action:");
			System.out.println(" 0: Show all members of Club");
			System.out.println(" 1: Add member to Club");
			System.out.println(" 2: Add pet to Club's member");
			System.out.println(" 3: Remove pet from Club's member");
			System.out.println(" 4: Remove member from Club");
			System.out.println(" 5: Remove some pet from all owners");
			System.out.println(" 6: Show Zoo Club List");
			System.out.println(" -: Exit");
			System.out.println("------------------------------------------");
			
			String res = sc.nextLine();
			
			switch (res) {
			case "0":
				System.out.println("> List of all members of the Club:");
				Iterator<Entry<Person, List<Pet>>> iterator0 = map.entrySet().iterator();
				
				while (iterator0.hasNext()) {
					Entry<Person, List<Pet>> next = iterator0.next();
					System.out.println(" - name: " + next.getKey().getName() + "; age: " +  next.getKey().getAge() + ";");
				}
				
				break;
			case "1":
				System.out.println("> Add a member to Club");
				System.out.println("  Enter the name of the member:");
				
				name = sc.nextLine();
				
				System.out.println("  Enter age of the member:");
				int age = Integer.valueOf(sc.nextLine());
				
				map.put(new Person(name,  age), new ArrayList<>());
				break;
			case "2":
				System.out.println("> Add pet to Club's member");
				System.out.println(">> Enter name of member to add pet:");
				
				name = sc.nextLine();
				
				if (name.isEmpty()) {
					System.out.println("The name is empty! \nCancel operation...");
					break;
				}
				
				Iterator<Entry<Person, List<Pet>>> iterator2 = map.entrySet().iterator();
				exists = false;
				
				while (iterator2.hasNext()) {
					Entry<Person, List<Pet>> next = iterator2.next();
					
					if (next.getKey().getName().equalsIgnoreCase(name)) {
						exists = true;
						System.out.println(">> Enter name of pet:");
						petname = sc.nextLine();
						
						System.out.println(">> Choose pet type by number:");
					
						int	ind = 1;
						for (PetsType type : petstype) {
							System.out.println(" - " + ind + ": " + type);
							ind++;
						}
						
						int pettype = Integer.valueOf(sc.nextLine()) - 1;
						
						next.getValue().add(new Pet(petstype[pettype], petname));
					}
					
				}
				
				if (!exists) System.out.println("  The member " + name + " doesn't exist");
				
				break;
			case "3":
				System.out.println("> Remove pet from Club's member");
				System.out.println(">> Enter name of member to remove pet:");
				name = sc.nextLine();
				
				if (name.isEmpty()) {
					System.out.println("The name is empty! \nCancel operation...");
					break;
				}
				
				Iterator<Entry<Person, List<Pet>>> iterator3 = map.entrySet().iterator();
				exists = false;
				
				while (iterator3.hasNext()) {
					Entry<Person, List<Pet>> next = iterator3.next();
					
					if (next.getKey().getName().equalsIgnoreCase(name)) {
						List<Pet> pets = next.getValue();
						System.out.println("\nThe member " + name + " has the next pets:");
						for (Pet pet : pets) {
							System.out.println(" - " + pet.getPettype() + " " + pet.getName()); 
						}
						
						System.out.println(">> Enter name of pet:");
						petname = sc.nextLine();
						Pet pet;
						
						for (int i = 0; i < pets.size() - 1; i++) {
							pet = pets.get(i);
							if (pet.getName().equalsIgnoreCase(petname)) {
								pets.remove(i);
								exists = true;
								System.out.println("  Pet " + petname + " was removed!");
							}
						}
						
						if (!exists) System.out.println("  Pet " + petname + " doesn't exist!");
					}
				}
				
				break;
			case "4":
				System.out.println("> Enter the name of the member to remove from Club:");
				name = sc.nextLine();
				
				if (name.isEmpty()) {
					System.out.println("The name is empty! \nCancel operation...");
					break;
				}
				
				Iterator<Entry<Person, List<Pet>>> iterator4 = map.entrySet().iterator();
				exists = false;
				
				while (iterator4.hasNext()) {
					Entry<Person, List<Pet>> next = iterator4.next();
					if (next.getKey().getName().equalsIgnoreCase(name)) {
						iterator4.remove();
						exists = true;
						System.out.println("  Member " + name + " was removed!");
					}
				}
				
				if (!exists) System.out.println("  Member " + name + " doesn't exist!");
				break;
			case "5":
				System.out.println("> Enter name of some pet to remove from all owners:");
				petname = sc.nextLine();
				
				System.out.println(">> Choose pet type by number:");
				
				int	ind = 1;
				for (PetsType type : petstype) {
					System.out.println(" - " + ind + ": " + type);
					ind++;
				}
				
				int pettype = Integer.valueOf(sc.nextLine()) - 1;
								
				
				Iterator<Entry<Person, List<Pet>>> iterator5 = map.entrySet().iterator();
				exists = false;
				
				while (iterator5.hasNext()) {
					Entry<Person, List<Pet>> next = iterator5.next();
				
					Iterator<Pet> petsiterator = next.getValue().iterator();
						
					while (petsiterator.hasNext()) {
						Pet pet = petsiterator.next();
						if ((pet.getName().equalsIgnoreCase(petname)) && (pet.getPettype().equals(petstype[pettype]))) {
							petsiterator.remove();
							exists = true;
						}			
					}
				}
				
				if (exists) {
					System.out.println("  The " + petstype[pettype] + " with the name " + petname + " was successfully removed from all members!");
				} else { 
					System.out.println("  The " + petstype[pettype] + " with the name " + petname + " doesn't exist!");
				}
				
				break;
			case "6":
				System.out.println("***[Zoo Club List:]***");
	
				Iterator<Entry<Person, List<Pet>>> iterator6 = map.entrySet().iterator();
			
				while (iterator6.hasNext()) {
					Entry<Person, List<Pet>> next = iterator6.next();
				
					Iterator<Pet> petsiterator = next.getValue().iterator();
					System.out.println(">> The member " + next.getKey().getName() + "[age = " + next.getKey().getAge() + "] has next pets:");
					while (petsiterator.hasNext()) {
						Pet pet = petsiterator.next();					
						System.out.println("   - " + pet.getPettype().toString().toLowerCase() +  " " + pet.getName());		
					}
					
					System.out.println();
				}
				
				break;
			case "-":
				System.exit(0);
				break;
			
			default:
				break;
			}
		}
	}
}
