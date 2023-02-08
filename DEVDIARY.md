# DEV DIARY - API Invoker

## Introduction
### Build or not Build? That is the question...
Travaillant en tant que chef de projet informatique dans le public une question récurrente
revient souvent, surtout en cette période de vaches maigres (nous sommes en février 2023, L'état nous indique qu'il n'y
a plus d'argent, qu'il va falloir reculer l'âge de départ à la retraite, les syndicats bravent le froid d'hiver
vachement doux réchauffé par le changement climatique pour combattre les nantis de la finance qui dirigent la politique
et le monde !) (Et toi qui travailles aussi dans le public et qui lis ces mots en 2060, tu te rends compte que la
situation n'a pas changé, mais que comme pour moi on te demande de maintenir/créer/gérer de nouveaux projets avec toujours
moins de moyens :D), *Build or not Build ? That is the question...*

Devons-nous payer pour faire ce que l'on a besoin ou devons-nous le construire par nos propres moyens ?

### Du build pour quoi faire ?
Question que se pose tous non-informaticiens (dans le public ou privé). On vous dira de faire une note d'opportunité,
suivi d'une réunion d'information qui découlera sur réunion de mise en place, qui ira ensuite sur un comité, puis à un
Comité de Projet (COPROJ pour les intimes ;) ). Après on mettra en place un planning que l'on ne pourra pas tenir parce
que le projet est une demande d'une sombre personnalité qui pense qu'il suffit d'appuyer sur un bouton ou mieux, qui a
déjà la solution puisqu'un commercial :D lui a montré une solution qui fonctionne déjà... en image :D XD !

Et ça n'est pas fini, assis au milieu de tous ces chefs de projets à la tête bien faite et à la prose magnifique tu te
demandes : Est-ce que l'on a déjà construit une maison avec seulement des architectes ? Tu te demandes où sont les
maçons qui vont construire la bâtir ?

À cela on te répondra : "un prestataire mon cher", qui prendra 3 fois ta paie, qui l'insèrera dans un calendrier qui
lui plaira avec pour résultat une solution qui ne répondra qu'en partie à tes besoins parce que tu comprends, il faut
qu'elle soit généralisé pour qu'il puisse la vendre à d'autres établissements publics et si tu veux lui dire m*rde, il
te répondra que c'est un marché de niche et qu'il est le seul à pouvoir répondre à ta demande à prix d'or :D !

À cela tu réponds : F*ck, quand je commande un burger, c'est par pour avoir des p*t*n de chickens wings ! Ça reste
du fast food mais ce n'est pas ce que j'ai demandé !!!!

Puis, tu te rappelles ta vie lorsque que tu étais pauvre et étudiant à la fac, que tu étais entouré seulement de maçons,
la vie dans l'informatique, c'était vivre la startup. Un copain parlait d'une idée, vous en parliez rapidement à 3 4, et
vous vous disiez "ouais, ça peut le faire" avec plus ou moins de temps, et vous sortiez vos claviers, vos couteaux et
une machine à café et vous codiez, re-parliez, faisiez vos blagues et on bouclait sur ça jusqu'à que le projet sorte.
Et tu te dis : c'était artisanal, mais ça marchait.

Enfin, je conclurai avec : en informatique, on peut tout construire, mais pour cela il faut une demande claire et
précise. Et surtout par avoir peur d'essayer, de se planter et de faire mieux.

**PS : Ce projet est sous licence GNU-GPL, c'est-à-dire que vous pouvez :**
* Exécuter le logiciel, pour n'importe quel usage ;
* Étudier le fonctionnement d'un programme et de l'adapter à ses besoins, ce qui passe par l'accès aux codes sources ;
* La liberté de redistribuer des copies ;
* **L'obligation de faire bénéficier la communauté des versions modifiées.** (Hé oui amis de la prestation de service et
  du privé, il faut faire tourner la roue, quand tu prends il faut aussi donner sinon malheur à toi).

## DAY ONE: 2023-02-07
### Why this... ?
Parlons architecture, cette librairie à pour base de répondre à la demande projet (DNA pour Dossier Numérique de
l'Agent). Celle-ci souhaite qu'un applicatif sur SI (Alfresco), puisse s'interfacer avec Pastell, une application tierce
mettant à disposition une API. Dans cet objectif, et dans le but de répondre à des besoins futur en développement,
c'est-à-dire la connexion entre deux API, j'ai décidé de mettre en place cette librairie générale qui devrai me fournir
une architecture qui me permettra d'implémenter les appels vers une API (en JAVA) rapidement (parce que qu'on est comme
ça dans le public on voit loin :D ).

### Le noyau
Le noyau est ce dont a besoin la librairie de base pour fonctionner de manière minimal. Dans ce projet, ce seront
les éléments créés dans le dossier ou devrais-je dire package (pour respecter le patois JAVA)
<code>baobab.librairies.requete.noyau</code>. Le code contenu dans ce package va fournir les méthodes nécessaires pour
exécuter des requêtes HTTP simples vers une URL indiquée.

### Ca se complique... pour encore mieux simplifier
L'exercice simple est effectué. On le complique en ajoutant une couche d'authentication basic en Base 64 et en y 
ajoutant les méthodes d'appels. Bizarre, ça ressemble exactement aux appels que l'on doit effectuer pour appeler l'API 
Pastell. Ces classes sont disponibles dans le package <code>baobab.librairies.requete.authentifie.base64</code>.

### Un service ? Qu'est-ce que c'est ?
Mise en place d'une interface de service qui est en fait une requête déguisée accédant à une ressource de l'hôte sur 
lequel l'on se connecte. Cette interface est disponible dans le package 
<code>baobab.librairies.requete.service</code>. Les nouveaux services devront implémenter l'interface *IService* et par 
extension l'interface *IRequeteHTTP*.