package com.alagezia37.archivedocuments.demo;

public class Main {

	public static void main(String[] args) {
	        ClassLoader loader = Main.class.getClassLoader();
	        System.out.println(loader.getResource("org/apache/commons/logging/impl/SLF4JLocationAwareLog.class"));
	}
}
