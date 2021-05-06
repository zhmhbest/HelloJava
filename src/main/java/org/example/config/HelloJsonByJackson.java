package org.example.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class HelloJsonByJackson {
    static class Person {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Person person = new Person();
        person.setName("Tom");
        person.setAge(40);

        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        System.out.println(jsonString);

        Person deserializedPerson = mapper.readValue(jsonString, Person.class);
        System.out.println(deserializedPerson);
    }
}
