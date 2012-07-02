package net.example.backend.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Define the entity class at the corresponding dao.
 * Needed for AbstractDao implementation
 * 
 * @author andre
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Dao {
	@SuppressWarnings("rawtypes")
	Class entityClass();
}
