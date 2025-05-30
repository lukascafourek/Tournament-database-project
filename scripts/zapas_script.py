import random
import itertools
import psycopg2
import datetime

conn = psycopg2.connect(
    database="cafoulu1",
    user="cafoulu1",
    password="<PASSWORD>",
    host="slon.felk.cvut.cz",
    port="5432"
)
cursor = conn.cursor()
cursor.execute("SELECT nazev FROM turnaj")
results = cursor.fetchall()
turnaje = [row[0] for row in results]
teams = ["Virtus Pro", "Natus Vincere", "Astralis", "FaZe Clan", "Fnatic"]
maps = ["Ancient", "Anubis", "Inferno", "Mirage", "Nuke", "Overpass", "Vertigo"]
def generate_matches():
    for turnaj in turnaje:
        team_combinations = list(itertools.combinations(teams, 2))
        for _ in range(3):
            for (tym_A, tym_B) in team_combinations:
                mapa = random.choice(maps)
                delka_min = random.randint(40, 60)
                datum_konani = random_date_for_tournament(turnaj)
                match_data = {
                    "mapa": mapa,
                    "tym_A": tym_A,
                    "tym_B": tym_B,
                    "datum_konani": datum_konani,
                    "vitezny_tym": tym_A,
                    "delka_min": delka_min,
                    "nazev_turnaje": turnaj
                }
                cursor.execute("""
                    INSERT INTO zapas (mapa, tym_a, tym_b, datum_konani, vitezny_tym, delka_min, nazev_turnaje) 
                    VALUES (%s, %s, %s, %s, %s, %s, %s)""", tuple(match_data.values()))
                conn.commit()

def random_date_for_tournament(turnaj):
    global year
    if turnaj.endswith("One"):
        year = 2014
    elif turnaj.endswith("Two"):
        year = 2015
    elif turnaj.endswith("Three"):
        year = 2016
    elif turnaj.endswith("Four"):
        year = 2017
    datum_konani = datetime.date(year, random.randint(7, 8), random.randint(1, 28))
    return datum_konani.strftime("%Y-%m-%d")

generate_matches()
conn.close()
