package com.example.zenhabit.classes
/**
 * La clase JardiCard tindrá les dades necesaries per a mostrar les diferents targetes del RecyclerView ubicat al Fragment de JardiFragment.
 * Ens donará informació del Jardí que te l'usuari,
 * en aquest cas quantitat de plantes i arbres.
 *
 * @param imgReward Icona de la recompensa
 * @param rewardName Nom de la recompensa
 * @param amountReward Quantitat
 */
class JardiCard(
    var imgReward: Int,
    var rewardName: String?,
    var amountReward: Int
    )
{
}