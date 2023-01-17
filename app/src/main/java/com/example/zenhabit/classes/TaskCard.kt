package com.example.zenhabit.classes
/**
 * La clase TaskCard representa les dades necessàries per mostrar les diferents targetes de tasques al RecyclerView situat al fragment TaskAndHabits.
 * Dona informació de les tasques programades per l'usuari.
 *
 * @param tascaNom Nom de la tasca
 * @param tascaDescripcio Descripció de la tasca
 * @param tascaCategoria Categoria de la tasca
 * @param tascaTemps Temps de la tasca
 * @param edicio Flag per editar
 */
class TaskCard(
    var tascaNom: String?,
    var tascaDescripcio: String?,
    var tascaCategoria: String?,
    var tascaTemps: String?,
    var edicio: Boolean
):java.io.Serializable
{
}