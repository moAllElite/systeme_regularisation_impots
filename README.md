#   systeme de régularisation des impôts
### lancez les containeur  docker de postgresql et pgadmin avec terminal avec la commande `docker-compose up `
### Le pgadmin se lance au port `localhost:5050`
 les identifiants sont email :  ```` admin@email.com ```` 
mot de passe   ```admin``` 
### Plus de détails consultés le fichier compose.yml


![img.png](img.png)
## Ajout déclarant
![img_1.png](img_1.png)
##   contrainte de validation ajout déclarant depuis le dto
![img_7.png](img_7.png)
## Liste des déclarants
![img_2.png](img_2.png)
## Pagination de la liste
![img_3.png](img_3.png)
## Ajout déclaration
![img_4.png](img_4.png)
## Pagination de  la liste des déclarations
![img_5.png](img_5.png)
## Ajout d'un  paiement
![img_6.png](img_6.png)
## Liste de paiement
### Redevance restant ou montant dû
Ici la redevance restant représente la soustraction entre le
montant de déclaration et le montant payé.
Une fois une déclaration completement réglée
la redevance est égale à 0 dans ce cas on désactive le bouton paiement de l'utilisateur 
![img_9.png](img_9.png)
#### Désactivation du bouton paiement 
![img_10.png](img_10.png)
