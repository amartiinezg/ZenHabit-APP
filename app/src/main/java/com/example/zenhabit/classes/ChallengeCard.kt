package com.example.zenhabit.classes

/**La clase ChallengeCard tindrá les dades necesaries per a mostrar les diferents targetes del RecyclerView
* ubicat al Fragment de TaskAndHabits. Ens donará informació dels Challenges o Reptes que te l'usuari
* en aquell moment.
*
*
* @param challengeName Nom del repte
* @param challengeReward Nom de la recompensa
* @param challengeCompleted Flag per indicar si esta complert
*/

class ChallengeCard(
    var challengeName: String,
    var challengeReward: String,
    var challengeCompleted: Boolean
)
{
}