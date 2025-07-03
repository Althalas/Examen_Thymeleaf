package com.humanbooster.Examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application Spring Boot
 * 
 * Cette classe contient la méthode main qui démarre l'application.
 * L'annotation @SpringBootApplication combine :
 * - @Configuration : Marque la classe comme source de définitions de beans
 * - @EnableAutoConfiguration : Active l'auto-configuration Spring Boot
 * - @ComponentScan : Active le scan des composants dans le package
 */
@SpringBootApplication
public class ExamenApplication {

	/**
	 * Point d'entrée principal de l'application
	 * 
	 * Cette méthode démarre le contexte Spring et lance l'application web.
	 * Elle utilise SpringApplication.run() pour initialiser et démarrer
	 * le serveur embarqué (Tomcat par défaut).
	 * 
	 * @param args Arguments de ligne de commande passés à l'application
	 */
	public static void main(String[] args) {
		SpringApplication.run(ExamenApplication.class, args);
	}

}
