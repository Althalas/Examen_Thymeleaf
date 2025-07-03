package com.humanbooster.Examen.model;

/**
 * Énumération des statuts possibles pour une tâche
 * 
 * Cette énumération définit les différents états qu'une tâche peut avoir
 * au cours de son cycle de vie. Les statuts suivent une progression logique
 * du travail à faire vers le travail terminé.
 */
public enum TaskStatus {
    
    /**
     * Tâche à faire
     * Statut initial d'une nouvelle tâche
     */
    TODO,
    
    /**
     * Tâche en cours de réalisation
     * Statut intermédiaire quand le travail a commencé
     */
    IN_PROGRESS,
    
    /**
     * Tâche terminée
     * Statut final quand le travail est complètement achevé
     */
    DONE
}
