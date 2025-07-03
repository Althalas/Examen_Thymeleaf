package com.humanbooster.Examen.model;

import lombok.Data;

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
     */
    private String username;
}