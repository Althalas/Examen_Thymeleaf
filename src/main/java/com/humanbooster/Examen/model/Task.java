package com.humanbooster.Examen.model;

import lombok.Data;

/**
 * Modèle représentant une tâche dans l'application
 * 
 * Cette classe définit la structure d'une tâche avec ses propriétés
 * de base. Elle utilise l'annotation @Data de Lombok pour générer
 * automatiquement les getters, setters, toString, equals et hashCode.
 * 
 * Une tâche appartient à un projet et peut être assignée à un utilisateur.
 * Elle a un statut qui évolue au cours de son cycle de vie.
 */
@Data
public class Task {
    
    /**
     * Identifiant unique de la tâche
     * Généré automatiquement lors de la création
     */
    private Long id;
    
    /**
     * Titre de la tâche
     * Description courte de ce qui doit être fait
     */
    private String title;
    
    /**
     * Statut actuel de la tâche
     * Peut être TODO, IN_PROGRESS ou DONE
     */
    private TaskStatus status;
    
    /**
     * Utilisateur assigné à la tâche
     * Peut être null si aucune assignation n'a été faite
     */
    private User assignee;
    
    /**
     * Projet auquel appartient la tâche
     * Référence vers l'objet Project parent
     */
    private Project project;
}
