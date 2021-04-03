**Authors:** Parrino Alessandro, Rui Filipe, Tomic Mario and Wilfried Karel.

<!-- ABOUT THE PROJECT -->
## About The Project

This software allows the generation of a static HTML website, It was developed in the context of a school project at HEIG-VD.

### Built With
The following technologies were used to develop the project:
* [Maven](https://maven.apache.org/)
* [picocli](https://picocli.info/)
* [JUnit 5](https://junit.org/junit5/)



<!-- GETTING STARTED -->
## Getting Started

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/gen-classroom/projet-ngueukam_parrino_tomic_lopes.git
   ```
2. Open a terminal and run
   ```sh
   mvn clean install
   ```



<!-- USAGE EXAMPLES -->
## Usage
Here is a list of available commands: 

* Create a basic project
 
 `statique init PATH`

 * Create static HTML site

 `statique build PATH`

 * Clear current project

 `statique clean PATH`

* See current version

 `statique --version`

* Coming soon
 
 `statique serve PATH`

After creating the template with the `init` command, you can edit the index.md according to your needs, and then use the ``build`` command to create your static site.
Here is the structure of the base sheet :

**Metadata:**
 ```sh
titre: Mon premier article  
auteur: Nom Prenom 
date: YY-MM-DD
   ```
**Delimiter**

``---``

**Content**
 ```sh
# Mon premier article
## Mon sous-titre
le contenu de mon article
![Une image](./image.png)
   ```
**Result Example**
 ```sh
<h1>Mon premier article</h1>  
<h2>Mon sous-titre</h2>  
<p>le contenu de mon article</p>  
<img alt="Une image" src="./image.png" >
  ```
<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Parrino Alessandro : alessandro.parrino@heig-vd.ch

Rui Filipe: rui.lopesgouveia@heig-vd.ch

Tomic Mario: mario.tomic@heig-vd.ch

Wilfried Karel: wilfriedkarel.ngueukamdjeuda@heig-vd.ch

Project Link: https://github.com/gen-classroom/projet-ngueukam_parrino_tomic_lopes
