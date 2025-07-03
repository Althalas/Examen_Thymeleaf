package com.humanbooster.Examen.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

/**
 * Modèle représentant un projet dans l'application
 * 
 * Cette classe définit la structure d'un projet avec ses propriétés
 * de base. Elle utilise l'annotation @Data de Lombok pour générer
 * automatiquement les getters, setters, toString, equals et hashCode.
 * 
 * Un projet contient une liste de tâches et a un créateur (utilisateur).
 */
@Data
public class Project {
    
    /**
     * Identifiant unique du projet
     * Généré automatiquement lors de la création
     */
    private Long id;
    
    /**
     * Nom du projet
     * Utilisé pour l'identification et l'affichage
     * Doit être non vide et entre 3 et 100 caractères
     */
    @NotBlank(message = "Le nom du projet est obligatoire")
    @Size(min = 3, max = 100, message = "Le nom du projet doit contenir entre 3 et 100 caractères")
    private String name;
    
    /**
     * Utilisateur qui a créé le projet
     * Référence vers l'objet User créateur
     * Doit être non null
     */
    @NotNull(message = "Le créateur du projet est obligatoire")
    private User creator;
    
    /**
     * Liste des tâches associées au projet
     * Initialisée avec une ArrayList vide par défaut
     */
    private List<Task> tasks = new ArrayList<>();
}
