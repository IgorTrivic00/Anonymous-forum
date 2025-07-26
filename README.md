#  Anonymous Forum

> Komande za pokretanje aplikacije

1. mvn clean package
2. Pre komande docker-compose up --build Gašenje i potpuno uklanjanje svega sto je docker-compose up napravio. (docker-compose down -v)
3. docker-compose up --build

---

> Ovo su test primeri za koriscenje aplikacije. Podaci vec postoje u bazi, ali mozete koristiti bilo sta drugo.

---

##  POST

###  Kreiranje posta

**POST** `http://localhost:8081/api/posts`

```json
{
  "content": "Moj omiljeni film je Intouchables, komedija po istinitom dogadjaju. Film je jako interesantan i zanimljiv. Da li ste gledali film i koji su vasi utisci?",
  "postType": "MARKDOWN",
  "user": {
    "username": "Igor123",
    "profilePicture": "igor.png"
  },
  "link": "https://www.youtube.com/watch?v=0RqDiYnFxTk"
}
```

```json
{
  "content": "Moj omiljeni film je The big short, tematika filma je svetska ekonomska kriza 2008. godine. To je jedan od najboljih filmova koje sam gledao. Kako se Vama cini film?",
  "postType": "MARKDOWN",
  "user": {},
  "link": "https://www.youtube.com/watch?v=vgqG3ITMv1Q"
}
```

```json
{
  "content": "Sto niko ne prica o nasim domacim filmovima, mnogo su interesantniji. Recimo Lepa sela lepo gore. Kako se Vama cini film?",
  "postType": "PLAIN",
  "user": {}
}
```

---

###  Vracanje svih postova (najstariji -> najnoviji)

**GET** `http://localhost:8081/api/posts`

```json
[
  {
    "id": 2,
    "user": {
      "id": 2,
      "username": "user-1469534b-d849-46fa-8673-af2f7aa03a39",
      "profilePicture": null
    },
    "content": "Moj omiljeni film je The big short...",
    "createdAt": "2025-07-26T14:10:32",
    "postType": "MARKDOWN",
    "link": "https://www.youtube.com/watch?v=vgqG3ITMv1Q",
    "whoa": "Whoa! Evil breath!"
  },
  {
    "id": 1,
    "user": {
      "id": 1,
      "username": "Igor123",
      "profilePicture": "igor.png"
    },
    "content": "Moj omiljeni film je Intouchables...",
    "createdAt": "2025-07-26T14:06:27",
    "postType": "MARKDOWN",
    "link": "https://www.youtube.com/watch?v=0RqDiYnFxTk",
    "whoa": "Whoa!"
  }
]
```

---

###  Vraćanje korisnikovih postova

**GET** `http://localhost:8081/api/posts/Igor123`

```json
[
  {
    "content": "Moj omiljeni film je Intouchables...",
    "postType": "MARKDOWN",
    "link": "https://www.youtube.com/watch?v=0RqDiYnFxTk",
    "createdAt": "2025-07-26 14:06:27",
    "author": "Igor123"
  }
]
```

---

###  Brisanje posta

**DELETE** `http://localhost:8081/api/posts/3`

---

## 👤 USER

###  Menjanje profilne slike

**PUT** `http://localhost:8081/api/users/picture`

```json
{
  "id": 2,
  "profilePicture": "zmaj.jpg"
}
```

---

###  Menjanje korisničkog imena

**PUT** `http://localhost:8081/api/users/2?username=joVan`

---

##  COMMENTS

###  Kreiranje komentara

**POST** `http://localhost:8081/api/comments`

```json
{
  "postId": 1,
  "content": "U taj film je fantastičan, u top 10 po mom mišljenju! Moja preporuka.",
  "user": {}
}
```

```json
{
  "postId": 2,
  "content": "Film mi se iskreno ne sviđa, previše je konfuzan, teško ga je razumeti",
  "user": {
    "username": "sasa239"
  }
}
```

---

###  Odgovor na komentare

**POST** `http://localhost:8081/api/comments/reply`

```json
{
  "postId": 1,
  "parentCommentId": 2,
  "content": "Slazem se film je savrsen, umrla sam od smeha hahahaha!",
  "user": {
    "username": "Jana9981"
  }
}
```

```json
{
  "postId": 1,
  "parentCommentId": 2,
  "content": "Meni je nekako tezak, zapravo jako tuzan film!",
  "user": {}
}
```

---

###  Brisanje komentara

**DELETE** `http://localhost:8081/api/comments/6`

---

###  Vracanje odgovora na odredjeni komentar

**GET** `http://localhost:8081/api/comments/replies/2`

---

###  Vracanje svih komentara nekog posta

**GET** `http://localhost:8081/api/comments/post/1`
