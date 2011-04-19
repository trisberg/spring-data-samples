package core;

import java.util.Set;

import org.springframework.data.graph.annotation.GraphProperty;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.data.graph.neo4j.annotation.Indexed;

@NodeEntity
public class Person {

    @GraphProperty
    @Indexed
	private String name;

    @RelatedTo(type = "KNOWS", elementClass = Person.class, direction = Direction.BOTH)
    private Set<Person> friends;

    // must have default constructor
	public Person() {
	}

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
    public void addFriend(Person person)
    {
        this.relateTo(person, "KNOWS");
    }

    public boolean isFriendsWith(Person person)
    {
        return this.friends.contains(person);
    }

	public String toString() {
		return this.getName();
	}

	public String friendsToString() {
		return friends.size() + " friend(s): " + friends;
	}
}
