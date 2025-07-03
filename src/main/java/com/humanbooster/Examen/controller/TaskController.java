package com.humanbooster.Examen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.humanbooster.Examen.model.Task;
import com.humanbooster.Examen.model.User;
import com.humanbooster.Examen.model.Project;
import com.humanbooster.Examen.model.TaskStatus;
import com.humanbooster.Examen.service.TaskService;
import com.humanbooster.Examen.service.UserService;
import com.humanbooster.Examen.service.ProjectService;

/**
 * Contrôleur pour la gestion des tâches
 * 
 * Ce contrôleur gère toutes les requêtes HTTP liées aux tâches :
 * - Liste des tâches
 * - Création de nouvelles tâches
 * - Mise à jour du statut des tâches
 * 
 * Il utilise les services TaskService, UserService et ProjectService
 * pour récupérer et manipuler les données.
 */
@Controller
public class TaskController {
    
    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;

    /**
     * Constructeur avec injection des dépendances
     * 
     * @param taskService Service de gestion des tâches
     * @param userService Service de gestion des utilisateurs
     * @param projectService Service de gestion des projets
     */
    public TaskController(TaskService taskService, UserService userService, ProjectService projectService) {
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
    }

    /**
     * Affiche la liste de toutes les tâches
     * 
     * Cette méthode récupère toutes les tâches depuis le service
     * et les passe au template pour affichage.
     * 
     * @param model Modèle Spring pour passer des données à la vue
     * @return Nom du template à afficher (list-task.html)
     */
    @GetMapping("/tasks")
    public String listTasks(Model model) {
        model.addAttribute("title", "Tâches");
        model.addAttribute("tasks", taskService.getAllTasks());
        return "list-task";
    }
    
    /**
     * Affiche le formulaire de création d'une tâche
     * 
     * Cette méthode prépare le formulaire en récupérant les listes
     * des utilisateurs et des projets disponibles pour les sélections.
     * 
     * @param model Modèle Spring pour passer des données à la vue
     * @return Nom du template à afficher (create-task.html)
     */
    @GetMapping("/tasks/create")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("title", "Créer une Tâche");
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("projects", projectService.getAllProjects());
        return "create-task";
    }

    /**
     * Traite la création d'une nouvelle tâche
     * 
     * Cette méthode reçoit les données du formulaire de création,
     * récupère l'utilisateur assigné et le projet par leurs IDs,
     * définit le statut initial à TODO et sauvegarde la tâche.
     * Redirige ensuite vers la liste des tâches.
     * 
     * @param task Tâche à créer (récupérée depuis le formulaire)
     * @param assigneeId Identifiant de l'utilisateur assigné (optionnel)
     * @param projectId Identifiant du projet auquel appartient la tâche
     * @return Redirection vers la liste des tâches
     */
    @PostMapping("/tasks/create")
    public String createTask(@ModelAttribute Task task, 
                           @RequestParam(value = "assignee.id", required = false) Long assigneeId,
                           @RequestParam("project.id") Long projectId) {
        // Récupérer l'utilisateur assigné
        if (assigneeId != null) {
            User assignee = userService.getUserById(assigneeId);
            task.setAssignee(assignee);
        }
        
        // Récupérer le projet
        Project project = projectService.getProjectById(projectId);
        task.setProject(project);
        
        // Définir le statut initial à TODO
        task.setStatus(TaskStatus.TODO);
        
        taskService.createTask(task);
        return "redirect:/tasks";
    }
    
    /**
     * Met à jour le statut d'une tâche
     * 
     * Cette méthode permet de changer le statut d'une tâche
     * (TODO → IN_PROGRESS → DONE). Elle redirige ensuite vers
     * la page de détails du projet auquel appartient la tâche.
     * 
     * @param id Identifiant de la tâche à mettre à jour
     * @param newStatus Nouveau statut à appliquer
     * @return Redirection vers les détails du projet
     */
    @PostMapping("/tasks/{id}/status")
    public String updateTaskStatus(@PathVariable Long id, @RequestParam String newStatus) {
        taskService.updateTaskStatus(id, newStatus);
        return "redirect:/projects/" + taskService.getTaskById(id).getProject().getId();
    }
}
