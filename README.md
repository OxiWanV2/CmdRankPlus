# CmdRankPlus

CmdRankPlus est un plugin Spigot/Paper Minecraft permettant d'exécuter des commandes personnalisées basées sur les permissions et les rangs définis dans un fichier de configuration. Ce plugin est conçu pour être flexible et facile à configurer.

---

## ✨ Fonctionnalités

- **Commandes déclencheurs personnalisables** : Ajoutez des commandes comme `/shops` ou `/menu` dans le fichier `config.yml`.
- **Actions basées sur les rangs** : Associez des actions spécifiques à des rangs (ex. `/say {player} boutique1`).
- **Placeholders dynamiques** : Utilisez `{player}` et `{rank}` pour personnaliser les actions.
- **Commande de rechargement** : Rechargez la configuration sans redémarrer le serveur.
- **Gestion des permissions** : Contrôlez l'accès aux fonctionnalités via LuckPerms ou tout autre gestionnaire de permissions.

---

## 📂 Installation

1. Téléchargez le fichier JAR du plugin depuis la section [Releases](#).
2. Placez le fichier dans le dossier `plugins` de votre serveur Minecraft.
3. Redémarrez votre serveur pour générer le fichier `config.yml`.
4. Configurez les commandes et rangs dans le fichier `config.yml`.

---

## ⚙️ Configuration (`config.yml`)

Voici un exemple de configuration :

```yaml
commands:
  shops:
    rang1: "/shop {player} boutique1"
    rang2: "/shop {player} boutique2"
    rang3: "/shop {player} boutique3"
  menu:
    rang1: "/menu {player} menu1"
    rang2: "/menu {player} menu2"
    rang3: "/menu {player} menu3"
```

### Explications :
- **`commands`** : Liste des commandes déclencheurs (ex. `/shops`, `/menu`).
- **`rangX`** : Actions spécifiques associées à chaque rang.
  - `{player}` : Correspond au pseudo du joueur.
  - `{rank}` : Correspond au nom du rang.

---

## 🛠️ Commandes

### Principales :
| Commande               | Description                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| `/cmdrankplus reload`  | Recharge la configuration (rangs et actions associées).                     |
| `/cmdrankplus help`    | Affiche un guide détaillé sur le fonctionnement du plugin.                  |

### Notes :
- **⚠ Rechargement des commandes déclencheurs** : Pour ajouter de nouvelles commandes comme `/shops`, vous devez redémarrer le serveur Minecraft.

---

## 🔑 Permissions

### Permissions principales :
| Permission             | Description                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| `cmdrankplus.reload`   | Permet d'exécuter la commande `/cmdrankplus reload`. Par défaut pour les OPs.|

### Gestion des permissions par rang :
Pour associer un utilisateur à un rang, ajoutez une permission comme `cmdrankplus.rangX` via LuckPerms ou un autre gestionnaire de permissions.

Exemple avec LuckPerms :
```bash
/lp group <nom_du_groupe> permission set cmdrankplus.rang1 true
```

---

## 📝 Exemples d'utilisation

1. Un joueur avec la permission `cmdrankplus.rang1` exécute `/shops`.
   - Le plugin exécute la commande configurée pour `rang1`, par exemple `/shop <joueur> boutique1`.

2. Un administrateur souhaite recharger la configuration après modification :
   - Il exécute `/cmdrankplus reload`.

---

## 🛑 Limitations

- Les nouvelles commandes déclencheurs (ex. `/shops`) nécessitent un redémarrage du serveur pour être détectées.
- Les permissions doivent être définies dans `config.yml` avant d'être utilisées avec LuckPerms pour l'auto-complétion.

---

## 📄 Licence

Ce projet est sous licence MIT. Consultez le fichier [LICENSE](LICENSE) pour plus d'informations.
