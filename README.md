# MBRNewsAPI

##Disclaimer

Temps de réalisation 7h (avec un blockage de 1h30 -_-" sur un duplicat de classes due aux versions kotlin importées par défaut sur android studio kotlin 1.8 et compose 1.2 --> pas compatibles )

Affichage sommaire

##Choix d'architecture

J'ai utilisé une architecture MVVM pour permettre de tester le comportement des écrans de façon "logique"

Dans le découpage de l'application je me suis basé sur les principes de découpage en module pour permettre à des équipes de travailler en parallèle sur les modules de manière séparées mais aussi pour permettre de fournir une interface à chaque module qui laisserai la possibilité de switcher l'implémentation de modules sans pour autant impacter l'application (les autres couches).

Pourquoi rx, parce que j'avais pas le temps et comme je maîtrise mieux, c'est plus simple