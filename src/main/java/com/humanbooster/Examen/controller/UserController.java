package com.humanbooster.Examen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.humanbooster.Examen.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.humanbooster.Examen.model.User;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

/**
 * Contrôleur pour la gestion des utilisateurs
 * 
 * Ce contrôleur gère toutes les requêtes HTTP liées aux utilisateurs :
 * - Liste des utilisateurs
 * - Création de nouveaux utilisateurs
 * 
 * Il utilise le service UserService pour récupérer et manipuler les données.
 */
@Controller
public class UserController {
    
    private final UserService userService;

    /**
     * Constructeur avec injection des dépendances
     * 
     * @param userService Service de gestion des utilisateurs
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Affiche la liste de tous les utilisateurs
     * 
     * Cette méthode récupère tous les utilisateurs depuis le service
     * et les passe au template pour affichage en tableau.
     * 
     * @param model Modèle Spring pour passer des données à la vue
     * @return Nom du template à afficher (list-user.html)
     */
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("title", "Utilisateurs");
        model.addAttribute("users", userService.getAllUsers());
        return "list-user";
    }
    
    /**
     * Affiche le formulaire de création d'un utilisateur
     * 
     * Cette méthode prépare le formulaire de création d'utilisateur.
     * 
     * @param model Modèle Spring pour passer des données à la vue
     * @return Nom du template à afficher (create-user.html)
     */
    @GetMapping("/users/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("title", "Créer un utilisateur");
        model.addAttribute("user", new User());
        return "create-user";
    }
    
    /**
     * Traite la création d'un nouvel utilisateur
     * 
     * Cette méthode reçoit les données du formulaire de création,
     * valide les données et sauvegarde le nouvel utilisateur.
     * En cas d'erreur de validation, retourne au formulaire avec les erreurs.
     * 
     * @param user Utilisateur à créer (récupéré depuis le formulaire)
     * @param bindingResult Résultat de la validation
     * @param model Modèle Spring pour passer des données à la vue
     * @return Redirection vers la liste des utilisateurs ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/users/create")
    public String createUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Créer un utilisateur");
            return "create-user";
        }
        
        userService.createUser(user);
        return "redirect:/users";
    }
}
