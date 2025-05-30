import random
import datetime
import psycopg2

def generate_id():
    return random.randint(10000000, 99999999)

def choose_country(countries, i):
    if i < 6: return countries[0]
    elif i < 12: return countries[1]
    else: return countries[2]

def generate_player_data(i):
    first_names = ["Lars", "Mikkel", "Mads", "Peter", "Rasmus", "Magnus",
                   "Oliver", "Harry", "Jack", "James", "Charlie", "George",
                   "Noah", "Liam", "William", "James", "Lucas", "Benjamin"]
    last_names = ["Larsen", "Sorensen", "Jensen", "Nielsen", "Rasmussen", "Lagerstedt",
                  "Smith", "Jones", "Brown", "Williams", "Taylor", "Jackson",
                  "Johnson", "Miller", "Smith", "Rodriguez", "Davis", "Holland"]
    nicknames = ["noob", "hax", "camper", "smurf", "tryhard", "afk",
                 "tank", "healer", "dps", "fragger", "jungler", "carry",
                 "cheeseball", "scrub", "sweat", "troll", "bot", "pwned"]
    countries = ["Dánsko", "Spojené Státy Americké", "Velká Británie"]
    date = datetime.date(random.randint(1985, 1999), random.randint(1, 12), random.randint(1, 28))
    format_date = date.strftime("%Y-%m-%d")
    since_date = datetime.date(random.randint(2013, 2013), random.randint(1, 12), random.randint(1, 28))
    format_since_date = since_date.strftime("%Y-%m-%d")
    return {
        "cislo_smlouvy": generate_id(),
        "jmeno": first_names[i],
        "prijmeni": last_names[i],
        "prezdivka_ve_hre": nicknames[i],
        "datum_narozeni": format_date,
        "zeme_puvodu": choose_country(countries, i),
        "clenem_od": format_since_date
    }

conn = psycopg2.connect(
    database="cafoulu1",
    user="cafoulu1",
    password="<PASSWORD>",
    host="slon.felk.cvut.cz",
    port="5432"
)
cursor = conn.cursor()
teams = ["Astralis", "FaZe Clan", "Fnatic"]
for i in range(18):
    player_data = generate_player_data(i)
    if i < 6: player_data['nazev_tymu'] = teams[0]
    elif i < 12: player_data['nazev_tymu'] = teams[1]
    else: player_data['nazev_tymu'] = teams[2]
    cursor.execute("""
        INSERT INTO profesional (cislo_smlouvy, jmeno, prijmeni, prezdivka_ve_hre, datum_narozeni, zeme_puvodu, clenem_od, nazev_tymu)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s)""", tuple(player_data.values()))
conn.commit()
conn.close()
