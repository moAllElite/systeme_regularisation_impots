#   systeme de régularisation des impôts
Ce système va permettre de mettre aux usagers d'effectuer les paiements de leurs impôts:

Declarant (id long, raisonSociale String, adresse String, email String, telephone String)

Declaration(id long, dateDeclaration date, montantDeclaration  double, idDeclarant long)

Paiement(id long, datePaiement Date, montantPaiement double, idDeclaration long)

Il est possible pour un déclarant d'effectuer plusieurs paiements d'une même déclaration.
