package com.humanbooster.Examen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.humanbooster.Examen.model.Project;
import com.humanbooster.Examen.model.User;
import com.humanbooster.Examen.service.ProjectService;
import com.humanbooster.Examen.service.UserService;
import com.humanbooster.Examen.service.TaskService;

/**
 * Contrôleur pour la gestion des projets
 * 
 * Ce contrôleur gère toutes les requêtes HTTP liées aux projets :
 * - Affichage de la page d'accueil
 * - Liste des projets
 * - Détails d'un projet
 * - Création de nouveaux projets
 * 
 * Il utilise les services ProjectService, UserService et TaskService
 * pour récupérer et manipuler les données.
 */
@Controller
public class ProjectController {
    
    private final ProjectService projectService;
    private final UserService userService;
    private final TaskService taskService;

    /**
     * Constructeur avec injection des dépendances
     * 
     * @param projectService Service de gestion des projets
     * @param userService Service de gestion des utilisateurs
     * @param taskService Service de gestion des tâches
     */
    public ProjectController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    /**
     * Affiche la page d'accueil de l'application
     * 
     * Cette méthode gère la requête GET sur la racine "/" et affiche
     * la page d'accueil avec le menu principal.
     * 
     * @param model Modèle Spring pour passer des données à la vue
     * @return Nom du template à afficher (index.html)
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Accueil");
        return "index";
    }

    /**
     * Affiche la liste de tous les projets
     * 
     * Cette méthode récupère tous les projets depuis le service
     * et les passe au template pour affichage en tableau.
     * 
     * @param model Modèle Spring pour passer des données à la vue
     * @return Nom du template à afficher (list-project.html)
     */
    @GetMapping("/projects")
    public String listProjects(Model model) {
        model.addAttribute("title", "Projets");
        model.addAttribute("projects", projectService.getAllProjects());
        return "list-project";
    }

    /**
     * Affiche les détails d'un projet spécifique
     * 
     * Cette méthode récupère un projet par son ID et affiche
     * ses informations ainsi que la liste de ses tâches.
     * Si le projet n'existe pas, redirige vers la liste des projets.
     * 
     * @param id Identifiant du projet à afficher
     * @param model Modèle Spring pour passer des données à la vue
     * @return Nom du template à afficher (details-project.html) ou redirection
     */
    @GetMapping("/projects/{id}")
    public String projectDetails(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return "redirect:/projects";
        }
        model.addAttribute("title", "Détails du projet");
        model.addAttribute("project", project);
        model.addAttribute("tasks", taskService.getTasksByProjectId(id));
        return "details-project";
    }

    /**
     * Affiche le formulaire de création d'un projet
     * 
     * Cette méthode prépare le formulaire en récupérant la liste
     * des utilisateurs disponibles pour la sélection du créateur.
     * 
     * @param model Modèle Spring pour passer des données à la vue
     * @return Nom du template à afficher (create-project.html)
     */
    @GetMapping("/projects/create")
    public String showCreateProjectForm(Model model) {
        model.addAttribute("title", "Créer un Projet");
        model.addAttribute("users", userService.getAllUsers());
        return "create-project";
    }

    /**
     * Traite la création d'un nouveau projet
     * 
     * Cette méthode reçoit les données du formulaire de création,
     * récupère l'utilisateur créateur par son ID et sauvegarde
     * le nouveau projet. Redirige ensuite vers la liste des projets.
     * 
     * @param project Projet à créer (récupéré depuis le formulaire)
     * @param creatorId Identifiant de l'utilisateur créateur
     * @return Redirection vers la liste des projets
     */
    @PostMapping("/projects/create-project")
    public String createProject(@ModelAttribute Project project, @RequestParam("creator.id") Long creatorId) {
        User creator = userService.getUserById(creatorId);
        project.setCreator(creator);
        projectService.createProject(project);
        return "redirect:/projects";
    }
}
