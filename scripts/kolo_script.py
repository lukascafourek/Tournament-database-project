import random
import psycopg2

conn = psycopg2.connect(
    database="cafoulu1",
    user="cafoulu1",
    password="<PASSWORD>",
    host="slon.felk.cvut.cz",
    port="5432"
)
cursor = conn.cursor()
cursor.execute("SELECT id, tym_a, tym_b FROM zapas OFFSET 3")
results = cursor.fetchall()
zapasy = [row[0] for row in results]
tymy = [(row[1], row[2]) for row in results]
nicknames1 = ["kawasaki", "nowa", "zeli", "wojtek", "dabrowski"]
nicknames2 = ["hey_ivan", "petra", "kuznyak", "sokol", "popovski"]
nicknames3 = ["noob", "hax", "camper", "smurf", "tryhard"]
nicknames4 = ["tank", "healer", "dps", "fragger", "jungler"]
nicknames5 = ["cheeseball", "scrub", "sweat", "troll", "bot"]
id_zapasu = 0
nejlepsi_hrac = ""
for zapas in zapasy:
    for i in range(1, 31):
        cislo = i
        delka_min = random.randint(1, 3)
        if i < 15:
            vitezny_tym = tymy[id_zapasu][1]
            if tymy[id_zapasu][1] == "Virtus Pro": nejlepsi_hrac = random.choice(nicknames1)
            elif tymy[id_zapasu][1] == "Natus Vincere": nejlepsi_hrac = random.choice(nicknames2)
            elif tymy[id_zapasu][1] == "Astralis": nejlepsi_hrac = random.choice(nicknames3)
            elif tymy[id_zapasu][1] == "FaZe Clan": nejlepsi_hrac = random.choice(nicknames4)
            elif tymy[id_zapasu][1] == "Fnatic": nejlepsi_hrac = random.choice(nicknames5)
        else:
            vitezny_tym = tymy[id_zapasu][0]
            if tymy[id_zapasu][0] == "Virtus Pro": nejlepsi_hrac = random.choice(nicknames1)
            elif tymy[id_zapasu][0] == "Natus Vincere": nejlepsi_hrac = random.choice(nicknames2)
            elif tymy[id_zapasu][0] == "Astralis": nejlepsi_hrac = random.choice(nicknames3)
            elif tymy[id_zapasu][0] == "FaZe Clan": nejlepsi_hrac = random.choice(nicknames4)
            elif tymy[id_zapasu][0] == "Fnatic": nejlepsi_hrac = random.choice(nicknames5)
        kolo_data = {
            "cislo": cislo,
            "id_zapasu": id_zapasu + 4,
            "vitezny_tym": vitezny_tym,
            "nejlepsi_hrac": nejlepsi_hrac,
            "delka_min": delka_min
        }
        cursor.execute("""
        INSERT INTO kolo (cislo, id_zapasu, vitezny_tym, nejlepsi_hrac, delka_min) 
        VALUES (%s, %s, %s, %s, %s)""", tuple(kolo_data.values()))
        conn.commit()
    id_zapasu += 1
conn.close()
