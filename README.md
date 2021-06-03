**Authors:** Parrino Alessandro, Rui Filipe Lopes Gouveia, Tomic Mario and Ngueukam Djeuda Wilfried Karel.

<!-- ABOUT THE PROJECT -->

## About The Project

This software allows the generation of a **static HTML website**, It was developed in the context of a school project at HEIG-VD.

### Built With

The following technologies were used to develop the project:

* [Maven](https://maven.apache.org/)
* [picocli](https://picocli.info/)
* [JUnit 5](https://junit.org/junit5/)
* [Freemarker](https://freemarker.apache.org/)



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

  `statique -version`

* Opens the site in your default browser

  `statique serve PATH`

After creating the template with the `init` command, you can edit the **index.md** according to your needs, and then use the ``build`` command to create your static site.

The configuration settings of the static site are on **config.json** like this:

```json
{
"domain": "www.mon-site.com",
"title": "Mon site"
}
```

Here is the structure of the base sheet :

**Metadata:**

 ```markdown
titre: Mon premier article  
auteur: Nom Prenom 
date: YY-MM-DD
 ```

**Delimiter**

``---``

**Content**

 ```markdown
# Mon premier article
## Mon sous-titre
le contenu de mon article
![Une image](./image.png)
 ```

**Result Example**

The file **layout.html** is the template of the static site. The content is inserted into the body of the template and the metadata in the head. Below result of the body of the template.

 ```html
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

Rui Filipe Lopes Gouveia: rui.lopesgouveia@heig-vd.ch

Tomic Mario: mario.tomic@heig-vd.ch

Ngueukam Djeuda Wilfried Karel: wilfriedkarel.ngueukamdjeuda@heig-vd.ch

Project Link: https://github.com/gen-classroom/projet-ngueukam_parrino_tomic_lopes
