# Manuel d'utilisation 

**Auteurs :** Parrino Alessandro, Rui Filipe Lopes Gouveia, Tomic Mario and Ngueukam Djeuda Wilfried Karel.

## Préface

En raison d'un problème avec l'exécutable détecté trop tard, le projet dans son état actuel ne peut être run qu'en utilisant un IDE comme IntelliJ. En temps normal, le programme s'exécute au travers d'une commande dans la console, au niveau du path de l'exécutable :

   ```
   java statique [commande] [argument]
   ```
   
La partie [commande] et [argument] devront être entrés comme argument au lancement de l'application dans l'IDE. Par exemple, là où la commande normale serait : `java statique build /mon/site`

L'argument à entrer dans l'IDE sera : `build /mon/site`

## Installation

1. Cloner le repo

   ```sh
   git clone https://github.com/gen-classroom/projet-ngueukam_parrino_tomic_lopes.git
   ```

2. Exécuter la commande suivante, dans le dossier du repo

   ```sh
   mvn clean install
   ```
3. Ouvrir le projet dans IntelliJ ou autre

## Génération d'un site

##### Initialisation du projet

Exécuter la command `init PATH`, PATH étant le chemin du dossier où sera crée notre projet. Dans ce dossier se trouvent les fichiers suivants :

* content/
** page.md
** image.jpg
* template/
* config.json
* index.md

##### Manipuler les fichiers

* Le fichier page.md 

Ce fichier se présente en deux parties, délimitées par `---`

** La partie metadata

 ```markdown
titre: Mon premier article  
auteur: Nom Prenom 
date: YY-MM-DD
 ```
 
 Cette partie permet de saisir les données de la page, comme le titre, l'auteur ou la date.

** La partie Contenu

 ```markdown
# Mon premier article
## Mon sous-titre
le contenu de mon article
![Une image](./image.png)
 ```

Un titre est crée avec un `#` en début de ligne. Le nombre de `*` détermine le style du titre. Par exemple, `##` va être converti en une balise de type `<h2>`.

Une image est ajoutée avec `![Une image](./image.png)`. Entre parenthèses se trouve le path relatif de l'image. Par défaut, le programme crée un fichier image au même niveau que le fichier page.md.

* Le fichier config.js

```json
{
"domain": "www.mon-site.com",
"title": "Mon site"
}
```

Ce fichier permet de saisir le domaine et le titre du site.

##### Génération et résultat

Afin de générer le site, exécuter la commande `build PATH`, PATH étant le chemin du dossier fourni à l'initialisation. On peut observer le résultat en utilisant la commande `serve PATH`. Cette seconde commande ouvre le navigateur par défaut et affiche le résultat de la compilation.

##### Quelques commandes et options supplémentaires

 * La commande clean

Au travers de la commande `clean PATH`, il est possible de supprimer le contenu du dossier dans lequel on travaille. 

 * L'option -watch

Les commandes `build` et `server` offrent la possibilité de continuer à compiler le site, dès qu'il y a un changement au niveau des fichiers. Ainsi, il n'est pas obligatoire de relancer la commande `build` à chaque fois qu'on effectue un changement. 



