package com.example.demo.test.entity;

public class Element extends Base {

	public static final String ELEMENT = "ELEMENT";
	String name;
	private double width;
	private double height;
	public int zIndex;

	public Element() {
	}
	public Element(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return super.toString() + "Element [name=" + name + ", width=" + width + ", height=" + height + ", zIndex=" + zIndex + "]";
	}

	public String getName() {
		return name;
	}

	public Element setName(String name) {
		this.name = name;
		return this;
	}

	public double getWidth() {
		return width;
	}

	public Element setWidth(double width) {
		this.width = width;
		return this;
	}

	public double getHeight() {
		return height;
	}

	public Element setHeight(double height) {
		this.height = height;
		return this;
	}

	public int getzIndex() {
		return zIndex;
	}

	public Element setZIndex(int zIndex) {
		this.zIndex = zIndex;
		return this;
	}
	
	public Element setZIndex(Integer zIndex) {
		this.zIndex = zIndex;
		return this;
	}

}
