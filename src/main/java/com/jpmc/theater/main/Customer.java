package com.jpmc.theater.main;

/**
 * Customer class holds customer information.
 * @updated March 28, 2023 by Andriy Kudryavtsev
 */
public class Customer {	
	private final int id;
    private final String name;
    
    /**
     * Constructor method taking customer unique global id and customer name as arguments.
     * @param id - customer id representing a unique global id of the customer
     * @param name - customer name
     */
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        return (id == ((Customer)o).id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "id:" + id + ", name:" + name;
    }
}
