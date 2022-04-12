package PerepisNaseleniya;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
//        for (Person per : persons) {
//            System.out.println(per);
//        }
        long count = persons.stream()
                .filter(person -> person.getAge() < 18) //берем возраст из класса Person
                .count();
        System.out.println(count);
        System.out.println();
       // List<String> family = new ArrayList<>();
        persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < 27)
                .filter(person -> person.getSex() == Sex.MAN)
                .map(person -> person.getFamily())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
               // .filter(person -> person.getAge() < 65)
                .filter(person -> person.getSex() == Sex.WOMAN ? person.getAge() < 60 :
                        person.getAge() < 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .forEach(System.out::println);




    }
}
