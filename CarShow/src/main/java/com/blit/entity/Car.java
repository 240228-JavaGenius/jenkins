package com.blit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String brand, model, color, registerNumber;
	@Column(name = "year-of-model")
	private int year;
	private int price;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")
	private Owner owner;

	public Car( String brand, String model, String color, String registerNumber, int year, int price,Owner owner) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
        this.owner=owner;
    }
	
	
	
	public Car(Long id, String brand, String model, String color, String registerNumber, int year, int price) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.registerNumber = registerNumber;
		this.year = year;
		this.price = price;
	}



	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Car() {
	}

	public Car(String brand, String model, String color, String registerNumber, int year, int price) {
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.registerNumber = registerNumber;
		this.year = year;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
