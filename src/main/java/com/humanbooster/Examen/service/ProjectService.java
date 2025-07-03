package com.humanbooster.Examen.service;

import org.springframework.stereotype.Service;
import com.humanbooster.Examen.model.Project;
import java.util.List;
import com.humanbooster.Examen.model.User;
import java.util.ArrayList;

/**
 * Service de gestion des projets
 * 
 * Cette classe fournit les opérations métier pour la gestion des projets.
 * Elle utilise un stockage en mémoire avec une liste statique pour persister
 * les données pendant l'exécution de l'application.
 * 
 * Le service gère la création, la récupération et la recherche de projets.
 */
@Service
public class ProjectService {
    /**
     * Liste statique des projets en mémoire
     * Initialisée avec des données de test
     */
    private static final List<Project> projects = new ArrayList<>(List.of(
        createProject(1L, "test du framework NestJs pour le stage", createUser(1L, "Martin")),
        createProject(2L, "test de l'application de la 'ratbank'", createUser(2L, "Virginie")),
        createProject(3L, "test de l'application de gestion personnages SWTOR", createUser(3L, "Vincent"))
    ));
    private static long idCounter = 4;
    
    /**
     * Méthode utilitaire pour créer un projet avec ID, nom et créateur
     * 
     * @param id Identifiant unique du projet
     * @param name Nom du projet
     * @param creator Créateur du projet
     * @return Project nouvellement créé
     */
    private static Project createProject(Long id, String name, User creator) {
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setCreator(creator);
        return project;
    }
    
    /**
     * Méthode utilitaire pour créer un utilisateur avec ID et nom
     * 
     * @param id Identifiant unique de l'utilisateur
     * @param username Nom de l'utilisateur
     * @return User nouvellement créé
     */
    private static User createUser(Long id, String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return user;
    }
    
    /**
     * Crée un nouveau projet
     * 
     * Génère automatiquement un ID unique et ajoute le projet
     * à la liste en mémoire.
     * 
     * @param project Projet à créer (l'ID sera généré automatiquement)
     */
    public void createProject(Project project) {
        project.setId(++idCounter);
        projects.add(project);
    }
    
    /**
     * Récupère tous les projets
     * 
     * @return Copie de la liste des projets pour éviter les modifications externes
     */
    public List<Project> getAllProjects() {
        return new ArrayList<>(projects);
    }
    
    /**
     * Recherche un projet par son ID
     * 
     * @param id Identifiant du projet à rechercher
     * @return Project trouvé ou null si aucun projet avec cet ID n'existe
     */
    public Project getProjectById(Long id) {
        return projects.stream()
                      .filter(project -> project.getId().equals(id))
                      .findFirst()
                      .orElse(null);
    }
}
