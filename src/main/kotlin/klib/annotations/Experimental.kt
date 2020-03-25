package klib.annotations

/**
 * Experimental Annotation for Experimental Public API Interfaces
 *
 * @since 0.1.5
 * @author Nils Rider
 */
@Target(AnnotationTarget.CLASS)
@RequiresOptIn("Experimental API that can change at any time", level = RequiresOptIn.Level.WARNING)
annotation class Experimental
