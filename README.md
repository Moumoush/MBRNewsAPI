# MBRNewsAPI

## Disclaimer

Temps de réalisation 7h (avec un blockage de 1h30 -_-" sur un duplicat de classes due aux versions kotlin importées par défaut sur android studio kotlin 1.8 et compose 1.2 --> pas compatibles )

Affichage sommaire

Je voulais ajouter room pour essayer de montrer le fonctionnement du repo avec deux sources de données pour montrer un cas il ferait un peut plus que simple mapper d'objets 

Et room est assez simple d'utilisation comme base de donnée même si elle stock les données en clair (il faut juste savoir quoi garder en clair et quoi sécuriser)

J'utilise Moshi pour le parsing json car je le trouve adapté à kotlin dans l'utilisation (simple)


## Fichier à part pour les libs

Pour éviter sur un seul projet avec plusieurs modules de gérer les versions de lib module par module et permettre de facilité plus tard la gestion des script de montés de versions/libs qui seront dépendant de la ci/cd mise en place.

## Choix d'architecture

J'ai utilisé une architecture MVVM pour permettre de tester le comportement des écrans de façon "logique"

Dans le découpage de l'application je me suis basé sur les principes de découpage en module pour permettre à des équipes de travailler en parallèle sur les modules de manière séparées mais aussi pour permettre de fournir une interface à chaque module qui laisserai la possibilité de switcher l'implémentation de modules sans pour autant impacter l'application (les autres couches).

Pourquoi rx, parce que j'avais pas le temps et comme je maîtrise mieux, c'est plus simple

## Injection de dépendances

J'ai utilisé Koin pour sa simplicité même si je n'ai pas passé beaucoup de temps à bien l'utiliser (je pense que j'en fais une mauvaise utilisation dans le découpage en module -> variables globales)

## Affichage

La navigation est faite sur un même écran au lieu de deux (faute de temps) et il manque un state à part de navigation pour la transition entre écrans.

Même chose le design system pourrait faire l'objet d'une librairie à part entière voir même géré dans un sdk à part qu'une équipe viendrait enrichir. 

J'ai vite fais utilisé glide compose même si il est pas en version tout à fais stable pour simplifier la gestion et le chargement des images à l'écran.


Le thème sur lequel j'ai développé était en darkmode sur mon téléphone (si il y a des soucis)

## Tests
J'ai utilisé JUNIT5 car de nombreuses fonctionnalités sont fournies pour organiser l'affichage des test à l'écran et le nomage, et aussi c'était plus rapide pour que je puisse lancer la configuration des flux pour éxécuter la récupération des états du viewmodel

Je n'avais plus de temps, j'ai donc abrégé en donnant un exemple de test unitaire sur la logique d'écran.
    - premier test sans interractions utilisateur
    - deuxième avec l'action du boutton de chargement effectué


## Sécurité

Pour la clef d'api je l'ajoute dans l'intercepteur http de manière pas très sécurisé, une des solutions possibles serait d'encoder la clef en base64 et/ou éventuellement de la charger depuis un fichier c++ ou de la protéger via une règle proguard.

Il est aussi possible sur le git d'utiliser gitcrypt pour éviter de les garder en clair dans le repo 
