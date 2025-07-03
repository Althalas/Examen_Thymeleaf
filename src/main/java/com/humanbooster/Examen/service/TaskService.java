package com.humanbooster.Examen.service;

import org.springframework.stereotype.Service;
import com.humanbooster.Examen.model.Task;
import com.humanbooster.Examen.model.Project;
import com.humanbooster.Examen.model.User;
import com.humanbooster.Examen.model.TaskStatus;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Service de gestion des tâches
 * 
 * Cette classe fournit les opérations métier pour la gestion des tâches.
 * Elle utilise un stockage en mémoire avec une liste statique pour persister
 * les données pendant l'exécution de l'application.
 * 
 * Le service gère la création, la récupération, la recherche et la mise à jour
 * des statuts des tâches.
 */
@Service
public class TaskService {
    
    /**
     * Liste statique des tâches en mémoire
     * Initialisée avec des données de test incluant des assignés et des projets
     */
    private static final List<Task> tasks = new ArrayList<>(List.of(
        createTask(1L, "Faire un test d'api", TaskStatus.TODO, createUser(1L, "Martin"), createProject(1L, "test du framework NestJs pour le stage")),
        createTask(2L, "Ajouter les currencys", TaskStatus.IN_PROGRESS, createUser(2L, "Virginie"), createProject(2L, "test de l'application de la 'ratbank'")),
        createTask(3L, "Tests de la création d'un personnage", TaskStatus.DONE, createUser(3L, "Vincent"), createProject(3L, "test de l'application de gestion personnages SWTOR"))
    ));
    
    /**
     * Compteur pour générer des IDs uniques
     * Commence à 4 car les 3 premiers IDs sont utilisés par les données de test
     */
    private static long idCounter = 4;
    
    /**
     * Méthode utilitaire pour créer un utilisateur avec ID et username
     * 
     * @param id Identifiant de l'utilisateur
     * @param username Nom d'utilisateur
     * @return User nouvellement créé
     */
    private static User createUser(Long id, String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return user;
    }
    
    /**
     * Méthode utilitaire pour créer un projet avec ID et nom
     * 
     * @param id Identifiant unique du projet
     * @param name Nom du projet
     * @return Project nouvellement créé
     */
    private static Project createProject(Long id, String name) {
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        return project;
    }
    
    /**
     * Méthode utilitaire pour créer une tâche complète
     * 
     * @param id Identifiant unique de la tâche
     * @param title Titre de la tâche
     * @param status Statut de la tâche
     * @param assignee Utilisateur assigné à la tâche
     * @param project Projet auquel appartient la tâche
     * @return Task nouvellement créée
     */
    private static Task createTask(Long id, String title, TaskStatus status, User assignee, Project project) {
        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setStatus(status);
        task.setAssignee(assignee);
        task.setProject(project);
        return task;
    }
    
    /**
     * Crée une nouvelle tâche
     * 
     * Génère automatiquement un ID unique et ajoute la tâche
     * à la liste en mémoire.
     * 
     * @param task Tâche à créer (l'ID sera généré automatiquement)
     */
    public void createTask(Task task) {
        task.setId(++idCounter);
        tasks.add(task);
    }
    
    /**
     * Récupère toutes les tâches
     * 
     * @return Copie de la liste des tâches pour éviter les modifications externes
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
    
    /**
     * Recherche une tâche par son ID
     * 
     * @param id Identifiant de la tâche à rechercher
     * @return Task trouvée ou null si aucune tâche avec cet ID n'existe
     */
    public Task getTaskById(Long id) {
        return tasks.stream()
                   .filter(task -> task.getId().equals(id))
                   .findFirst()
                   .orElse(null);
    }
    
    /**
     * Récupère toutes les tâches d'un projet spécifique
     * 
     * @param projectId Identifiant du projet
     * @return Liste des tâches appartenant au projet spécifié
     */
    public List<Task> getTasksByProjectId(Long projectId) {
        return tasks.stream()
                   .filter(task -> task.getProject() != null && task.getProject().getId().equals(projectId))
                   .collect(Collectors.toList());
    }
    
    /**
     * Met à jour le statut d'une tâche
     * 
     * @param taskId Identifiant de la tâche à mettre à jour
     * @param newStatus Nouveau statut à appliquer
     */
    public void updateTaskStatus(Long taskId, String newStatus) {
        Task task = getTaskById(taskId);
        if (task != null) {
            task.setStatus(TaskStatus.valueOf(newStatus));
        }
    }
}
