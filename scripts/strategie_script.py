import random
import psycopg2

def generate_player_data(data):
    strategy = ["sniper", "smg", "brokovnice", "utocna puska"]
    strategy2 = ["obrana", "utok", "obrana i utok"]
    return {
        "hrac": data[0],
        "strategie": random.choice(strategy) + ", " + random.choice(strategy2)
    }

conn = psycopg2.connect(
    database="cafoulu1",
    user="cafoulu1",
    password="<PASSWORD>",
    host="slon.felk.cvut.cz",
    port="5432"
)
cursor = conn.cursor()
cursor.execute("SELECT cislo_smlouvy FROM hrac OFFSET 10")
results = cursor.fetchall()
data = []
for row in results:
    data.append(row)
for i in range(15):
    player_data = generate_player_data(data[i])
    cursor.execute("""
        INSERT INTO strategie (hrac, strategie) 
        VALUES (%s, %s)""", tuple(player_data.values()))
conn.commit()
conn.close()
