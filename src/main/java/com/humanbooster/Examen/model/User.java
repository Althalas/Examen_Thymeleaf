package com.humanbooster.Examen.model;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Modèle représentant un utilisateur dans l'application
 * 
 * Cette classe définit la structure d'un utilisateur avec ses propriétés
 * de base. Elle utilise l'annotation @Data de Lombok pour générer
 * automatiquement les getters, setters, toString, equals et hashCode.
 * 
 * Un utilisateur peut être assigné à des tâches et peut créer des projets.
 */
@Data
public class User {
    
    /**
     * Identifiant unique de l'utilisateur
     * Généré automatiquement lors de la création
     */
    private Long id;
    
    /**
     * Nom d'utilisateur
     * Utilisé pour l'identification et l'affichage
     * Doit être non vide et entre 3 et 50 caractères
     */
    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @Size(min = 3, max = 50, message = "Le nom d'utilisateur doit contenir entre 3 et 50 caractères")
    private String username;
}