package com.humanbooster.Examen.service;

import org.springframework.stereotype.Service;
import com.humanbooster.Examen.model.User;
import java.util.List;
import java.util.ArrayList;

/**
 * Service de gestion des utilisateurs
 * 
 * Cette classe fournit les opérations métier pour la gestion des utilisateurs.
 * Elle utilise un stockage en mémoire avec une liste statique pour persister
 * les données pendant l'exécution de l'application.
 * 
 * Le service gère la création, la récupération et la recherche d'utilisateurs.
 */
@Service
public class UserService {
    
    /**
     * Liste statique des utilisateurs en mémoire
     * Initialisée avec des données de test
     */
    private static final List<User> users = new ArrayList<>(List.of(
        createUser(1L, "Martin"),
        createUser(2L, "Virginie"),
        createUser(3L, "Vincent")
    ));
    
    /**
     * Compteur pour générer des IDs uniques
     * Commence à 4 car les 3 premiers IDs sont utilisés par les données de test
     */
    private static long idCounter = 4;
    
    /**
     * Méthode utilitaire pour créer un utilisateur avec ID et username
     * 
     * @param id Identifiant unique de l'utilisateur
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
     * Crée un nouvel utilisateur
     * 
     * Génère automatiquement un ID unique et ajoute l'utilisateur
     * à la liste en mémoire.
     * 
     * @param user Utilisateur à créer (l'ID sera généré automatiquement)
     */
    public void createUser(User user) {
        user.setId(++idCounter);
        users.add(user);
    }
    
    /**
     * Récupère tous les utilisateurs
     * 
     * @return Copie de la liste des utilisateurs pour éviter les modifications externes
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    /**
     * Recherche un utilisateur par son ID
     * 
     * @param id Identifiant de l'utilisateur à rechercher
     * @return User trouvé ou null si aucun utilisateur avec cet ID n'existe
     */
    public User getUserById(Long id) {
        return users.stream()
                   .filter(user -> user.getId().equals(id))
                   .findFirst()
                   .orElse(null);
    }
}
