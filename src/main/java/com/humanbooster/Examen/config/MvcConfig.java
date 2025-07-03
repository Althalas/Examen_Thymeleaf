package com.humanbooster.Examen.config;

import java.util.Locale;

import org.springframework.lang.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Configuration MVC pour l'application
 * 
 * Cette classe configure les paramètres MVC de Spring, notamment :
 * - L'internationalisation (i18n) pour supporter plusieurs langues
 * - La résolution de locale basée sur les sessions
 * - L'intercepteur pour changer de langue via le paramètre 'lang'
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Configure le résolveur de locale pour l'internationalisation
     * 
     * Cette méthode définit comment Spring détermine la langue à utiliser.
     * Elle utilise SessionLocaleResolver pour stocker la langue dans la session
     * et définit le français comme langue par défaut.
     * 
     * @return SessionLocaleResolver configuré avec le français par défaut
     */
    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.FRENCH);
        return slr;
    }

    /**
     * Configure l'intercepteur pour changer de langue
     * 
     * Cet intercepteur permet de changer la langue de l'application
     * en ajoutant le paramètre 'lang' à l'URL (ex: ?lang=en, ?lang=fr).
     * Il intercepte toutes les requêtes et change la locale si le paramètre est présent.
     * 
     * @return LocaleChangeInterceptor configuré avec le paramètre 'lang'
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
     * Ajoute l'intercepteur de locale au registre des intercepteurs
     * 
     * Cette méthode enregistre l'intercepteur de changement de langue
     * pour qu'il soit appliqué à toutes les requêtes entrantes.
     * 
     * @param registry Registre des intercepteurs MVC
     */
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}